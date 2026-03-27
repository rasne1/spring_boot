package com.ktdsuniversity.edu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/*
 * End point 를 생성하는 역할.
 * */
@Controller //해당 클래스가 엔드포인트를 만들 수 있도록 지원해준다.
public class TestEndPointController {

	
	//Spring Application이 시작 될 때
	//@Controller가 적용된 모든 클래스를 찾아
	// 해당 클래스들을 인스턴스로 생성한다.
	// 생성된 인스턴스들은 Bean Container에 저장된다.
	public TestEndPointController() {
		System.out.println("TestEndPointController 인스턴스 만들어짐!");
		System.out.println(this);
	}
	/*
	 * "/JSP" 엔드포인트. 
	 *  -hellojsp.jsp 파일을 읽어서 HTML로 변형 시킨 후 결과를 반환.
	 * 
	 * */
	
	@GetMapping("/jsp")
	public String viewHelloJspPage(Model model) {
		// Model model parameter ==> template Engine(jsp)에게 데이터를 전송시키는 객체.
		//
		System.out.println(model);
		// myname 이라는 키(변수명)로 "오현석" 할당 해서 템플릿에게 전달.
		model.addAttribute("myname","오현석");
		model.addAttribute("age",36);
		System.out.println(model);
		
		// spring.mvc.view.prefix+hellojsp+spring.mvc.ciew.suffix
		// /WEB-INF/views/+hellojsp+.jsp
		// /WEB-INF/views/hellojsp.jsp
		//Controller 에서 String 타입을 반환시켰을 경우 
		// prefix + 반환값 + suffix의 경로를 
		// /src/main/webapp 아래에서 탐색한다.
		
		// 탐색한 jsp 파일을 HTML로 변환시켜 브라우저에게 전달.
		return "hellojsp";
	}
	
	
	/*
	 * 사용자가 "/root" 엔드포인트에 접근 하면 
	 * "첫 페이지입니다. 환영합니다" 를 브라우저에 보내주는 코드 작성.
	 * */
	@GetMapping("/root")
	public ResponseEntity<String> welcomePage(){
		return new ResponseEntity<>("첫 페이지 입니다. 환영합니다.", HttpStatus.OK);
	}
	
	// "/hello" 엔드포인트 생성.
	@GetMapping("/hello")
	// 사용자가 "/hello" 엔드포인트를 요청할 경우, 사용자에게 보여줄 HTML 페이지 생성.
	public ResponseEntity<String> viewHelloHTml(){
		//사용자에게 보여줄 HTML을 반환.
		return new ResponseEntity<>("Hello!", HttpStatus.OK);
	}
}
