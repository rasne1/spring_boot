package com.ktdsuniversity.edu.members.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;

import jakarta.validation.Valid;

@Controller
public class MembersController {
	
	
	@Autowired
	private MembersService MembersService;
	
	
	@ResponseBody
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateResultVO(@PathVariable String email) {
		
		MembersVO membersVO = this.MembersService.findMembersByEmail(email);
		
		DuplicateResultVO result = new DuplicateResultVO();
		
		result.setEmail(email);
		result.setDuplicate(membersVO != null);
		
		return result;
	}
	
	
	
	@GetMapping("/regist")
	public String createMembersPage() {
		
		return "members/regist";
	}
	
	@PostMapping("/regist")
	public String doSignUpAction(@Valid RegistVO registVO, BindingResult bindingResult, Model model ) {
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("inputData", registVO);
			return "members/regist";
		}
		
	boolean memberResult =this.MembersService.createNewMember(registVO);
		
		return "redirect:/list";
	}

}
