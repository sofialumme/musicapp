package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hh.swd20.musicapp.domain.AlbumRepository;

@Controller
public class AlbumController {
	
	@Autowired
	private AlbumRepository albumRepository;

}
