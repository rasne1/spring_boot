package com.ktdsuniversity.edu.tmdb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ktdsuniversity.edu.tmdb.dao.TmdbDao;
import com.ktdsuniversity.edu.tmdb.service.TmdbService;
import com.ktdsuniversity.edu.tmdb.vo.SearchResultVO;
import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;

@Controller
public class TmdbController {
	
	
	@Autowired
	private TmdbService tmdbService;
	
	
	@GetMapping("/list")
	public String viewMoviepage(Model model) {
		
		SearchResultVO searchResult = this.tmdbService.findAllBoard();
		
		
		List<TmdbVO> list = searchResult.getResult();
		
		int searchCount = searchResult.getCount();
		
		
		model.addAttribute("searchResult", list);
		
		model.addAttribute("searchCount", searchCount);
		
		
		return "tmdb/list";
	}
	
	
	@GetMapping("/write")
	public String viewWritePage() {
		
		return "tmdb/write";
	}
	
	@PostMapping("/write")
	public String doWriteAction(TmdbVO tmdbVO) {
		
		boolean createResult = this.tmdbService.createNewMovie(tmdbVO);
		
		return "redirect:/write";
		
	}
	
	
	
}
