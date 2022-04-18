package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.musicapp.domain.Album;
import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.ArtistRepository;
import hh.swd20.musicapp.domain.GenreRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

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
	
	@GetMapping("/albumlist")
	public String getAlbums(Model model) {
		model.addAttribute("albums", albumRepository.findAllSortByName());
		return "albumlist";
	}
	
	@GetMapping("/artistlist/{id}")
	public String getAlbumsByArtist(@PathVariable("id") Long artistId, Model model) {
		model.addAttribute("artist", artistRepository.findById(artistId).get());
		model.addAttribute("albums", albumRepository.findByArtistSortByYear(artistId));
		return "albumlistbyartist";
	}
	
	@GetMapping("/genrelist/{id}")
	public String getAlbumsByGenre(@PathVariable("id") Long genreId, Model model) {
		model.addAttribute("genre", genreRepository.findById(genreId).get());
		model.addAttribute("albums", albumRepository.findByGenreSortByName(genreId));
		return "albumlistbygenre";
	}
	
	@RequestMapping("/albumlist/add")
	public String addAlbum(Model model) {
		model.addAttribute("album", new Album());
		model.addAttribute("artists", artistRepository.findAllSortByName());
		model.addAttribute("genres", genreRepository.findAllSortByName());
		return "addalbum";
	}
	
	@PostMapping("/albumlist/save")
	public String saveAlbum(Album album) {
		albumRepository.save(album);
		return "redirect:/albumlist/edit/" + album.getId();
	}
	
	@RequestMapping("/albumlist/edit/{id}")
	public String editAlbum(@PathVariable("id") Long albumId, Model model) {
		model.addAttribute("album", albumRepository.findById(albumId).get());
		model.addAttribute("artists", artistRepository.findAllSortByName());
		model.addAttribute("genres", genreRepository.findAllSortByName());
		model.addAttribute("songs", songRepository.findByAlbumSortByTrackno(albumId));
		model.addAttribute("song", new Song());
		return "editalbum";
	}
	
	@GetMapping("/albumlist/delete/{id}")
	public String deleteAlbum(@PathVariable("id") Long albumId) {
		albumRepository.deleteById(albumId);
		return "redirect:/";
	}

}
