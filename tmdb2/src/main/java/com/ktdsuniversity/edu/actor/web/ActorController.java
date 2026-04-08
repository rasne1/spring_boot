package com.ktdsuniversity.edu.actor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.actor.service.ActorService;
import com.ktdsuniversity.edu.actor.vo.request.ActorWriteVO;

import jakarta.validation.Valid;

@Controller
public class ActorController {
	
	@Autowired
	private ActorService actorService;
	
	
	@GetMapping("/actor")
	public String viewActorWritePage() {
		
		return "actor/write";	
	}
	
	@PostMapping("/actor")
	public String doActorWriteAction(@Valid @ModelAttribute ActorWriteVO actorWriteVO, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("inputData", actorWriteVO );
			
			return "actor/write";
		}
		
		
		
		boolean creatResult = this.actorService.createNewActor(actorWriteVO);
		
		
		return "redirect:/actor";
		
	}

}
