package com.ktdsuniversity.edu.tmdb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Tmdb {
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		
		StringBuffer html = new StringBuffer();
		 html.append("<!DOCTYPE html>");
		 html.append("<html>");
		 html.append("<head><title>Hello Boot!</title></head>");
		 html.append("<body>");
		 html.append("<div>안녕하세요.</div>");
		 html.append("<div>Spring Boot에서 응답되었습니다!</div>");
		 html.append("</body>");
		 html.append("</html>");

		return new ResponseEntity<>(html.toString(), HttpStatus.OK);
	}

}
