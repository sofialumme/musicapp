package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.musicapp.domain.Genre;
import hh.swd20.musicapp.domain.GenreRepository;

@Controller
public class GenreController {
	
	@Autowired
	private GenreRepository genreRepository;
	
	@GetMapping("/genrelist")
	public String getGenres(Model model) {
		model.addAttribute("genres", genreRepository.findAllSortByName());
		return "genrelist";
	}
	
	@RequestMapping("/genrelist/add")
	public String addGenre(Model model) {
		model.addAttribute("genre", new Genre());
		return "addgenre";
	}
	
	@PostMapping("/genrelist/save")
	public String saveGenre(Genre genre) {
		genreRepository.save(genre);
		return "redirect:/genrelist";
	}
	
	@RequestMapping("/genrelist/edit/{id}")
	public String editGenre(@PathVariable("id") Long genreId, Model model) {
		model.addAttribute("genre", genreRepository.findById(genreId));
		return "editgenre";
	}

	@GetMapping("/genrelist/delete/{id}")
	public String deleteGenre(@PathVariable("id") Long genreId) {
		genreRepository.deleteById(genreId);
		return "redirect:/genrelist";
	}
}
