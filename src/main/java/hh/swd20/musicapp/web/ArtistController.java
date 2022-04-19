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

import hh.swd20.musicapp.domain.Artist;
import hh.swd20.musicapp.domain.ArtistRepository;

@CrossOrigin
@Controller
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;

	// user endpoints

	// list all artists
	@GetMapping("/artistlist")
	public String getArtists(Model model) {
		model.addAttribute("artists", artistRepository.findAllSortByName());
		return "artistlist";
	}

	// add a new artist
	@RequestMapping("/artistlist/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addArtist(Model model) {
		model.addAttribute("artist", new Artist());
		return "addartist";
	}

	// save an added artist
	@PostMapping("/artistlist/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveArtist(@Valid Artist artist, BindingResult result) {
		if (result.hasErrors()) {
			return "addartist";
			
		} else {
			artistRepository.save(artist);
			return "redirect:/artistlist";
		}
	}
	
	// save an added artist
		@PostMapping("/artistlist/saveedit")
		@PreAuthorize("hasAuthority('ADMIN')")
		public String saveArtistEdit(@Valid Artist artist, BindingResult result) {
			if (result.hasErrors()) {
				return "editartist";
				
			} else {
				artistRepository.save(artist);
				return "redirect:/artistlist";
			}
		}

	// edit an existing artist
	@RequestMapping("/artistlist/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editArtist(@PathVariable("id") Long artistId, Model model) {
		model.addAttribute("artist", artistRepository.findById(artistId));
		return "editartist";
	}

	// delete an artist (and all their albums and songs)
	@GetMapping("/artistlist/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteArtist(@PathVariable("id") Long artistId) {
		artistRepository.deleteById(artistId);
		return "redirect:/artistlist";
	}

	// REST endpoints

	// REST: list all artists alphabetically
	@GetMapping("/artists")
	public @ResponseBody List<Artist> artistListRest() {
		return (List<Artist>) artistRepository.findAllSortByName();
	}

	// REST: get a specific artist by id
	@GetMapping("/artists/{id}")
	public @ResponseBody Optional<Artist> findArtistByIdRest(@PathVariable("id") Long artistId) {
		return (Optional<Artist>) artistRepository.findById(artistId);
	}

}
