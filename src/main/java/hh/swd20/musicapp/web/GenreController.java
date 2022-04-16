package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hh.swd20.musicapp.domain.GenreRepository;

@Controller
public class GenreController {
	
	@Autowired
	private GenreRepository genreRepository;

}
