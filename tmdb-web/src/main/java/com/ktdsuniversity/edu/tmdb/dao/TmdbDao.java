package com.ktdsuniversity.edu.tmdb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jspecify.annotations.Nullable;

import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;

@Mapper
public interface TmdbDao {

	

	 int selectBoardCount();

	 List<TmdbVO> selectBoardList();

	 int insertNewMovie(TmdbVO tmdbVO);
	

	

}
