package com.apap.tutorial8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	@RequestMapping("/")
	public String home (Model model) {
		model.addAttribute("status", "");
		return "home";
	}
	
	@RequestMapping("/login")
	public String login () {
		return "login";
	}
}
