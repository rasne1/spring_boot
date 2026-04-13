package com.ktdsuniversity.edu.tmdb.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.tmdb.dao.TmdbDao;
import com.ktdsuniversity.edu.tmdb.enums.ReadType;
import com.ktdsuniversity.edu.tmdb.service.TmdbService;
import com.ktdsuniversity.edu.tmdb.vo.SearchResultVO;
import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;
import com.ktdsuniversity.edu.tmdb.vo.request.WriteVO;

import jakarta.validation.Valid;

@Controller
public class TmdbController {
	
	
	@Autowired
	private TmdbService tmdbService;
	
	
	@GetMapping("/list")
	public String viewMovieListpage(Model model) {
		
		SearchResultVO searchResult = this.tmdbService.findAllBoard();
		
		
		List<TmdbVO> list = searchResult.getResult();
		
		int searchCount = searchResult.getCount();
		
		
		model.addAttribute("searchResult", list);
		
		model.addAttribute("searchCount", searchCount);
		
		
		return "tmdb/list";
	}
	
	@GetMapping("/view/{movieId}")
	public String viewMovieDetailPage(Model model, @PathVariable String movieId) {
		
	TmdbVO findResult = this.tmdbService.findMovieByMovieId(movieId, ReadType.VIEW);
	
	model.addAttribute("movie", findResult);
		
		return "tmdb/view";
		
	}
	
	
	@GetMapping("/write")
	public String viewWritePage() {
		
		return "tmdb/write";
	}
	
	@PostMapping("/write")
	public String doWriteAction(@Valid @ModelAttribute WriteVO writeVO, BindingResult bindingResult, Model model) {
		
		System.out.println("클래스 = " + writeVO.getClass().getName()+"하하하하");
	    System.out.println("runningTime 값 = " + writeVO.getRunningTime());
		
		if(bindingResult.hasErrors()) {
			
			model.addAttribute("inputData", writeVO );
			
			
			return "tmdb/write";
			
		}
		
		boolean createResult = this.tmdbService.createNewMovie(writeVO);
		
		return "redirect:/write";
		
	}
	
	@GetMapping("/delete")
	public String deleteMoviePage(@RequestParam String MovieId) {
		
	boolean deleteResult =this.tmdbService.deleteMovieById(MovieId);
		
		return "tmdb/list";
		
	}
	
	
	
}
