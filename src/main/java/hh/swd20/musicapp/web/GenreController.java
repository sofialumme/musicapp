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

import hh.swd20.musicapp.domain.Genre;
import hh.swd20.musicapp.domain.GenreRepository;

@CrossOrigin
@Controller
public class GenreController {

	@Autowired
	private GenreRepository genreRepository;

	// user endpoints

	// list all genres
	@GetMapping("/genrelist")
	public String getGenres(Model model) {
		model.addAttribute("genres", genreRepository.findAllSortByName());
		return "genrelist";
	}

	// add a new genre
	@RequestMapping("/genrelist/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "addgenre";
	}

	// save an added genre
	@PostMapping("/genrelist/save")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveGenre(@Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			return "addgenre";

		} else {
			genreRepository.save(genre);
			return "redirect:/genrelist";
		}
	}

	// edit an existing genre
	@RequestMapping("/genrelist/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editGenre(@PathVariable("id") Long genreId, Model model) {
		model.addAttribute("genre", genreRepository.findById(genreId).get());
		return "editgenre";
	}

	// save an edited genre
	@PostMapping("/genrelist/saveedit")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String saveGenreEdit(@Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			return "editgenre";

		} else {
			genreRepository.save(genre);
			return "redirect:/genrelist";
		}
	}

	// delete a genre (and all its albums and songs)
	@GetMapping("/genrelist/delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteGenre(@PathVariable("id") Long genreId) {
		genreRepository.deleteById(genreId);
		return "redirect:/genrelist";
	}

	// REST endpoints

	// REST: list all genres alphabetically
	@GetMapping("/genres")
	public @ResponseBody List<Genre> genreListRest() {
		return (List<Genre>) genreRepository.findAllSortByName();
	}

	// REST: get a specific genre by id
	@GetMapping("/genres/{id}")
	public @ResponseBody Optional<Genre> findGenreByIdRest(@PathVariable("id") Long genreId) {
		return (Optional<Genre>) genreRepository.findById(genreId);
	}
}
