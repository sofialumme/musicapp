package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.swd20.musicapp.domain.ArtistRepository;

@Controller
public class ArtistController {
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

}
