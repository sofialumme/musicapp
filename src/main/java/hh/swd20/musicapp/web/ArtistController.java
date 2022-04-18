package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.musicapp.domain.Artist;
import hh.swd20.musicapp.domain.ArtistRepository;

@Controller
public class ArtistController {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/artistlist")
	public String getArtists(Model model) {
		model.addAttribute("artists", artistRepository.findAllSortByName());
		return "artistlist";
	}
	
	@RequestMapping("/artistlist/add")
	public String addArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "addartist";
	}
	
	@PostMapping("/artistlist/save")
	public String saveArtist(Artist artist) {
		artistRepository.save(artist);
		return "redirect:/artistlist";
	}
	
	@RequestMapping("/artistlist/edit/{id}")
	public String editArtist(@PathVariable("id") Long artistId, Model model) {
		model.addAttribute("artist", artistRepository.findById(artistId));
		return "editartist";
	}
	
	@GetMapping("/artistlist/delete/{id}")
	public String deleteArtist(@PathVariable("id") Long artistId) {
		artistRepository.deleteById(artistId);
		return "redirect:/artistlist";
	}

}
