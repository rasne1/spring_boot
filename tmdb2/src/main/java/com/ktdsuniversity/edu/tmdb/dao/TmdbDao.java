package com.ktdsuniversity.edu.tmdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;
import com.ktdsuniversity.edu.tmdb.vo.request.WriteVO;

@Mapper
public interface TmdbDao {

	

	 int selectBoardCount();

	 List<TmdbVO> selectBoardList();

	 int insertNewMovie(WriteVO writeVO);

	 int deleteMoiveById(String movieId);

	 TmdbVO selectMovieByMoviId(String movieId);
	

	

}
