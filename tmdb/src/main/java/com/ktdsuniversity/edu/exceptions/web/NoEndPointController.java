package com.ktdsuniversity.edu.exceptions.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class NoEndPointController {
	
	
	@GetMapping("/error")
	public String viewNotFoundPage(Model model) {
		
		model.addAttribute("errorMessage", "존재하지 않는 URL입니다.");
		
		return "errors/404";
	}


}
