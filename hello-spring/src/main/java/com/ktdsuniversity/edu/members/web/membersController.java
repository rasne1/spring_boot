package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktdsuniversity.edu.members.enums.ReadType;
import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.MembersVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.validation.Valid;

@Controller
public class membersController {

	@Autowired
	private MembersService membersService;
	
	@ResponseBody
	@GetMapping("/regist/check/duplicate/{email}")
	public DuplicateResultVO doCheckDuplicateEmailAction(@PathVariable String email) {
		//email이 이미 사용중인지 확인한다.
		MembersVO membersVO = this.membersService.findMemberByEmail(email);
		//확인된 결과를 브라우저에게 JSON으로 전송한다.
		// 이미 사용중 ==> {email : "test@gmail.com", duplicate:true}
		// 사용중이지 않음 ==> {email : "test@gmail.com", duplicate:false}
		DuplicateResultVO result = new DuplicateResultVO();
		result.setEmail(email);
		result.setDuplicate(membersVO != null);
		
		return result;
		
	}
	
	
	
	
	@GetMapping("/regist")
	public String createMembersPage() {
		return "members/member";
	}

	@PostMapping("/regist")
	public String doSignUpAction(@Valid @ModelAttribute MembersVO membersVO, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			
			model.addAttribute("inputData", membersVO);
			
			return "members/member";
			
		}
		
		boolean createResult = this.membersService.createNewMember(membersVO);

		return "redirect:/";

	}

	@GetMapping("/members/view")
	public String viewMemberListPage(Model model) {

		SearchResultVO searchResult = this.membersService.findAllMemberList();

		List<MembersVO> list = searchResult.getResult();
		int searchCount = searchResult.getCount();

		model.addAttribute("searchResult", list);
		model.addAttribute("searchCount", searchCount);

		return "members/list";
	}

	@GetMapping("/members/view/{email}")
	public String viewDetailPage(@PathVariable String email, Model model) {

		MembersVO findResult = this.membersService.findMemberByEmail(email);

		model.addAttribute("members", findResult);

		return "members/view";
	}

	@GetMapping("/members/update/{email}")
	public String updateMemberInfo(@PathVariable String email, Model model) {
		MembersVO findResult2 = this.membersService.findMemberByEmail(email);
		model.addAttribute("members", findResult2);

		return "members/update";
	}

	@PostMapping("/members/update/{email}")
	public String doUpdateAction(@PathVariable String email, MemberUpdateVO memberUpdateVO) {
		memberUpdateVO.setEmail(email);
		boolean updateResult = this.membersService.updateMemberByEmail(memberUpdateVO);
		System.out.println(email+updateResult+"지태");
		

		return "redirect:/members/view/" + email;
	}
	

	// /member ==> 회원들의 목록이 조회되도록 코드를 작성.
	// ==> 회원 목록 조회.
	// ==> members/list.jsp : 회원 목록 반복.
	// : 회원의 수 출력
	// : 회원의 수가 없을 때, "등록된 회원이 없습니다." 출력
	// : 목록 아래에는 "새로운 회원 등록 " 링크 추가.

}
