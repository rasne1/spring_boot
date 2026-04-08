package com.ktdsuniversity.edu.members.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ktdsuniversity.edu.board.service.BoardServiceImpl;
import com.ktdsuniversity.edu.members.enums.ReadType;
import com.ktdsuniversity.edu.members.service.MembersService;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.DuplicateResultVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class membersController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);

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
	public String doSignUpAction(@Valid @ModelAttribute RegistVO registVO, BindingResult bindingResult, Model model) {

		if(bindingResult.hasErrors()) {
			
			model.addAttribute("inputData", registVO);
			
			return "members/member";
			
		}
		
		boolean createResult = this.membersService.createNewMember(registVO);

		return "redirect:/";

	}

	@GetMapping("/members/view")
	public String viewMemberListPage(Model model) {

		SearchResultVO searchResult = this.membersService.findAllMemberList();

		List<RegistVO> list = searchResult.getResult();
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
		logger.debug("{} {} 지태",email,updateResult);
		//System.out.println(email+updateResult+"지태");
		

		return "redirect:/members/view/" + email;
	}
	

	// /member ==> 회원들의 목록이 조회되도록 코드를 작성.
	// ==> 회원 목록 조회.
	// ==> members/list.jsp : 회원 목록 반복.
	// : 회원의 수 출력
	// : 회원의 수가 없을 때, "등록된 회원이 없습니다." 출력
	// : 목록 아래에는 "새로운 회원 등록 " 링크 추가.

	
	
	@GetMapping("/login")
	public String viewLoginPage() {
		
		return "members/login";
	}
	
	@PostMapping("/login")
	public String doLoginAction(@Valid @ModelAttribute LoginVO loginVO, BindingResult bindingResult, Model model,
			@RequestParam(required = false, defaultValue = "/") String go,HttpServletRequest request){
		if(bindingResult.hasErrors()) {
			model.addAttribute("loginData", request);
			return "members/login";
		}
		
		String userIp = request.getRemoteAddr();
		loginVO.setIp(userIp);
		
		MembersVO member = this.membersService.findMemberByEmailAndPassword(loginVO);
		
		// 서버의 세션을 삭제한다.
		// 로그아웃.
		request.getSession().invalidate();
		
		// request.getSession(); <== HttpRequestHeader로 전달된 JSESSIONID의 객체를 반환.
		// request.getSession(true); <== 기존 JESSIONID로 발급된 세션 객체는 버리고, 새로운 ID의 세션 객체를 생성후 반환.
		HttpSession session = request.getSession(true);
		session.setAttribute("__LOGIN_DATA__", member);
		
		return "redirect:"+go;
		
	}
	
	@GetMapping("/delete-me")
	public String dodeleteAction(@SessionAttribute("__LOGIN_DATA__")MembersVO loginMember, HttpSession session) {
		// 1 로그인 세션에서 회원의 이메일을 가져온다.
		String memberEmail =loginMember.getEmail();
		// 2.Members 테이블에서 회원의 정보를 이메일을 이용해 삭제한다.
		boolean deleteSuccessMember = this.membersService.deletMemberInfo(memberEmail);
		logger.debug("회원삭제 성공? {}",deleteSuccessMember);
		//System.out.println("회원삭제 성공?"+deleteSuccessMember);
		// 3.현재 로그인된 사용자를 로그아웃 시킨다.
		session.invalidate();
		// 4. "members/deletesuccess" 페이지를 보여준다.
		return "members/deleteSuccess";
		//   "탈퇴가 완료됬습니다 다음에 다시만나요!"
		
		
		
	}
}
