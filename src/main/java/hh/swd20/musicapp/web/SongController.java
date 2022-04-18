package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.musicapp.domain.AlbumRepository;
import hh.swd20.musicapp.domain.Song;
import hh.swd20.musicapp.domain.SongRepository;

@Controller
public class SongController {

	@Autowired
	private SongRepository songRepository;
	
	@Autowired
	private AlbumRepository albumRepository;
	
	@GetMapping("/albumlist/{id}")
	public String getSongs(@PathVariable("id") long albumId, Model model) {
		model.addAttribute("album", albumRepository.findById(albumId).get());
		model.addAttribute("songs", songRepository.findByAlbumSortByTrackno(albumId));
		return "songlist";
	}
	
	@RequestMapping("/songlist/edit/{id}")
	public String editSong(@PathVariable("id") Long songId, Model model) {
		model.addAttribute("song", songRepository.findById(songId));
		model.addAttribute("album", songRepository.findById(songId).get().getAlbum());
		return "editsong";
	}
	
	@PostMapping("/songlist/save")
	public String saveSong(Song song) {
		songRepository.save(song);
		return "redirect:/albumlist/edit/" + song.getAlbum().getId();
	}
	
	@GetMapping("/songlist/delete/{id}")
	public String deleteSong(@PathVariable("id") Long songId) {
		Long albumId = songRepository.findById(songId).get().getAlbum().getId();
		songRepository.deleteById(songId);
		return "redirect:/albumlist/edit/" + albumId;
	}
	
}
