package com.ktdsuniversity.edu.tmdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.tmdb.dao.TmdbDao;
import com.ktdsuniversity.edu.tmdb.vo.SearchResultVO;
import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;

@Service
public class TmdbServiceImpl implements TmdbService {

	
	@Autowired
	private TmdbDao tmdbDao;

	@Override
	public SearchResultVO findAllBoard() {
		SearchResultVO result = new SearchResultVO();
		
		int count = this.tmdbDao.selectBoardCount();
		result.setCount(count);
		
		List<TmdbVO> list = this.tmdbDao.selectBoardList();
		
		result.setResult(list);
		
		return result;
	}

	@Override
	public boolean createNewMovie(TmdbVO tmdbVO) {
		int insertCount = tmdbDao.insertNewMovie(tmdbVO);
		return insertCount == 1;
	}

	
	


}
