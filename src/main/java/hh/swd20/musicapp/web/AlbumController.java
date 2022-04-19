package hh.swd20.musicapp.web;

import java.util.List;
import java.util.Optional;

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

import hh.swd20.musicapp.domain.Album;
import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.ArtistRepository;
import hh.swd20.musicapp.domain.GenreRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

@CrossOrigin
@Controller
public class AlbumController {

	@Autowired
	private AlbumRepository albumRepository;

	@Autowired
	private ArtistRepository artistRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private SongRepository songRepository;

	// user endpoints

	// list all albums
	@GetMapping("/albumlist")
	public String getAlbums(Model model) {
		model.addAttribute("albums", albumRepository.findAllSortByName());
		return "albumlist";
	}

	// list all albums by an artist
	@GetMapping("/artistlist/{id}")
	public String getAlbumsByArtist(@PathVariable("id") Long artistId, Model model) {
		model.addAttribute("artist", artistRepository.findById(artistId).get());
		model.addAttribute("albums", albumRepository.findByArtistSortByYear(artistId));
		return "albumlistbyartist";
	}

	// list all albums from a genre
	@GetMapping("/genrelist/{id}")
	public String getAlbumsByGenre(@PathVariable("id") Long genreId, Model model) {
		model.addAttribute("genre", genreRepository.findById(genreId).get());
		model.addAttribute("albums", albumRepository.findByGenreSortByName(genreId));
		return "albumlistbygenre";
	}

	// add a new album
	@RequestMapping("/albumlist/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addAlbum(Model model) {
		model.addAttribute("album", new Album());
		model.addAttribute("artists", artistRepository.findAllSortByName());
		model.addAttribute("genres", genreRepository.findAllSortByName());
		return "addalbum";
	}

	// save an added album
	@PostMapping("/albumlist/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveAlbum(@Valid Album album, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("artists", artistRepository.findAllSortByName());
			model.addAttribute("genres", genreRepository.findAllSortByName());
			return "addalbum";

		} else {
			albumRepository.save(album);
			return "redirect:/albumlist/edit/" + album.getId();
		}
	}

	// save an edited album
	@PostMapping("/albumlist/saveedit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveAlbumEdit(@Valid Album album, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("artists", artistRepository.findAllSortByName());
			model.addAttribute("genres", genreRepository.findAllSortByName());
			model.addAttribute("songs", songRepository.findByAlbumSortByTrackno(album.getId()));
			model.addAttribute("song", new Song());
			return "editalbum";

		} else {
			albumRepository.save(album);
			return "redirect:/albumlist/edit/" + album.getId();
		}
	}

	// edit an existing album and add songs to it
	@RequestMapping("/albumlist/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editAlbum(@PathVariable("id") Long albumId, Model model) {
		model.addAttribute("album", albumRepository.findById(albumId).get());
		model.addAttribute("artists", artistRepository.findAllSortByName());
		model.addAttribute("genres", genreRepository.findAllSortByName());
		model.addAttribute("songs", songRepository.findByAlbumSortByTrackno(albumId));
		model.addAttribute("song", new Song());
		return "editalbum";
	}

	// delete an album (and its songs)
	@GetMapping("/albumlist/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteAlbum(@PathVariable("id") Long albumId) {
		albumRepository.deleteById(albumId);
		return "redirect:/";
	}

	// REST endpoints

	// REST: list all albums alphabetically
	@GetMapping("/albums")
	public @ResponseBody List<Album> albumListRest() {
		return (List<Album>) albumRepository.findAllSortByName();
	}

	// REST: get a specific album by id
	@GetMapping("/albums/{id}")
	public @ResponseBody Optional<Album> findAlbumByIdRest(@PathVariable("id") Long albumId) {
		return (Optional<Album>) albumRepository.findById(albumId);
	}

	// REST: list all albums from an artist by id
	@GetMapping("/artists/{id}/albums")
	public @ResponseBody List<Album> listAlbumsByArtistIdRest(@PathVariable("id") Long artistId) {
		return (List<Album>) albumRepository.findByArtistSortByName(artistId);
	}

	// REST: list all albums from a genre by id
	@GetMapping("/genres/{id}/albums")
	public @ResponseBody List<Album> listAlbumsByGenreIdRest(@PathVariable("id") Long genreId) {
		return (List<Album>) albumRepository.findByGenreSortByName(genreId);
	}

}
