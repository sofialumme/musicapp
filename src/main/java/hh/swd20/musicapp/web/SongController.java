package hh.swd20.musicapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

@CrossOrigin
@Controller
public class SongController {

	@Autowired
	private SongRepository songRepository;

	@Autowired
	private AlbumRepository albumRepository;

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
	public String saveSong(Song song) {
		songRepository.save(song);
		return "redirect:/albumlist/edit/" + song.getAlbum().getId();
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
