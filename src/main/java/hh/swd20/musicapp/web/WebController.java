package hh.swd20.musicapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	// user endpoints

	// index page
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	// login page
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

}
