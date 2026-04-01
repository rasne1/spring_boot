package com.ktdsuniversity.edu.tmdb.service;

import java.util.List;

import com.ktdsuniversity.edu.tmdb.vo.SearchResultVO;
import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;


public interface TmdbService {

	SearchResultVO findAllBoard();

	boolean createNewMovie(TmdbVO tmdbVO);

	

	

}
