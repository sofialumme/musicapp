package hh.swd20.musicapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hh.swd20.musicapp.domain.SongRepository;

@Controller
public class SongController {

	@Autowired
	private SongRepository songRepository;
	
}
