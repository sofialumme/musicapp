package hh.swd20.musicapp.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.ArtistRepository;
import hh.swd20.musicapp.domain.GenreRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

@CrossOrigin
@Controller
public class SongController {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private AlbumRepository albumRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	// user endpoints

	// show an album's information and list its songs
	@GetMapping("/albumlist/{id}")
	public String getSongs(@PathVariable("id") long albumId, Model model) {
		model.addAttribute("album", albumRepository.findById(albumId).get());
		model.addAttribute("songs", songRepository.findByAlbumSortByTrackno(albumId));
		return "songlist";
	}

	// edit an existing song
	@RequestMapping("/songlist/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editSong(@PathVariable("id") Long songId, Model model) {
		model.addAttribute("song", songRepository.findById(songId));
		model.addAttribute("album", songRepository.findById(songId).get().getAlbum());
		return "editsong";
	}

	// save an added song
	@PostMapping("/songlist/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveSong(@Valid Song song, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("album", albumRepository.findById(song.getAlbum().getId()).get());
			model.addAttribute("artists", artistRepository.findAllSortByName());
			model.addAttribute("genres", genreRepository.findAllSortByName());
			model.addAttribute("songs", songRepository.findByAlbumSortByTrackno(song.getAlbum().getId()));
			return "editalbum";

		} else {
			songRepository.save(song);
			return "redirect:/albumlist/edit/" + song.getAlbum().getId();
		}
	}

	// save an edited song
	@PostMapping("/songlist/saveedit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveSongEdit(@Valid Song song, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("album", songRepository.findById(song.getId()).get().getAlbum());
			return "editsong";

		} else {
			songRepository.save(song);
			return "redirect:/albumlist/edit/" + song.getAlbum().getId();
		}
	}

	// delete a song
	@GetMapping("/songlist/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteSong(@PathVariable("id") Long songId) {
		Long albumId = songRepository.findById(songId).get().getAlbum().getId();
		songRepository.deleteById(songId);
		return "redirect:/albumlist/edit/" + albumId;
	}

	// REST endpoints

	// REST: list all songs from an album by id
	@GetMapping("/albums/{id}/songs")
	public @ResponseBody List<Song> listSongsByAlbumIdRest(@PathVariable("id") Long albumId) {
		return (List<Song>) songRepository.findByAlbumSortByTrackno(albumId);
	}

}
