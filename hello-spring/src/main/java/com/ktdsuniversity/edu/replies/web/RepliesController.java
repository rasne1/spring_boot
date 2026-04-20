package com.ktdsuniversity.edu.replies.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ktdsuniversity.edu.replies.service.RepliesService;
import com.ktdsuniversity.edu.replies.vo.response.SearchResultVO;

@Controller
public class RepliesController {
	
	private static final Logger logger = LoggerFactory.getLogger(RepliesController.class);
	
	@Autowired
	private RepliesService repliesService;
	
//	@GetMapping("/api/replies/{articleId}")
//	public SearchResultVO getRepliesList(@PathVariable String articleId) {
//		
//		this.repliesService.findRepliesByArticleId(articleId);
//		
//		
//		return null;
//	}
//	
	

}
