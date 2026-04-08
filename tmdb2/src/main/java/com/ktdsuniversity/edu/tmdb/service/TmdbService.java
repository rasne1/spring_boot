package com.ktdsuniversity.edu.tmdb.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.ktdsuniversity.edu.tmdb.enums.ReadType;
import com.ktdsuniversity.edu.tmdb.vo.SearchResultVO;
import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;
import com.ktdsuniversity.edu.tmdb.vo.request.WriteVO;


public interface TmdbService {

	SearchResultVO findAllBoard();

	boolean createNewMovie(WriteVO writeVO);

	boolean deleteMovieById(@RequestParam String MovieId);

	TmdbVO findMovieByMovieId(String movieId, ReadType view);

	

	

}
