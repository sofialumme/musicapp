package hh.swd20.musicapp.web;

import org.springframework.web.bind.annotation.RequestMapping;

public class WebController {
	
	// user endpoints
	
	// index page
	@RequestMapping("/")
	public String index() {
		return "index";
	}

}
