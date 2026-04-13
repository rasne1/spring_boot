package com.ktdsuniversity.edu.tmdb.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.request.UploadVO;
import com.ktdsuniversity.edu.tmdb.dao.TmdbDao;
import com.ktdsuniversity.edu.tmdb.enums.ReadType;
import com.ktdsuniversity.edu.tmdb.vo.SearchResultVO;
import com.ktdsuniversity.edu.tmdb.vo.TmdbVO;
import com.ktdsuniversity.edu.tmdb.vo.request.WriteVO;

@Service
public class TmdbServiceImpl implements TmdbService {

	
	@Autowired
	private TmdbDao tmdbDao;
	
	@Autowired
	private FilesDao filesDao;

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
	public boolean createNewMovie(WriteVO writeVO) {
		int insertCount = tmdbDao.insertNewMovie(writeVO);
		
		List<MultipartFile> attachFiles =writeVO.getAttachFiles();
		
		if(attachFiles !=null && attachFiles.size() > 0) {
			for(int i=0; i<attachFiles.size(); i++) {
				
				File storeFile = new File("c://uploadfiles", attachFiles.get(i).getOriginalFilename());
				if(!storeFile.getParentFile().exists()) {
					storeFile.getParentFile().mkdirs();
				}
				try {
					attachFiles.get(i).transferTo(storeFile);
					UploadVO uploadVO = new UploadVO();
					String filename = attachFiles.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".")+1);
					uploadVO.setFileGroupId(writeVO.getMovieId());
					uploadVO.setObfuscateName(filename);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFile.length());
					uploadVO.setFilePath(storeFile.getAbsolutePath());
					
					this.filesDao.insertAttachFile(uploadVO);
					
				} catch (IllegalStateException | IOException e) {
					
					e.printStackTrace();
				}
				
			}

		}
		
		return insertCount == 1;
	}

	@Override
	public boolean deleteMovieById(@RequestParam String MovieId) {
		
		int deleteResult = this.tmdbDao.deleteMoiveById(MovieId);
		
		return deleteResult == 1;
	}

	@Override
	public TmdbVO findMovieByMovieId(String movieId, ReadType readType) {
		if(readType == ReadType.VIEW) {
			
			TmdbVO tmdb=this.tmdbDao.selectMovieByMoviId(movieId);
			
		}
		
		return this.tmdbDao.selectMovieByMoviId(movieId);
	}

	
	


}
