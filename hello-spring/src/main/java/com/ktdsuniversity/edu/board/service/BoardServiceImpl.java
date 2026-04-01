package com.ktdsuniversity.edu.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.board.dao.BoardDao;
import com.ktdsuniversity.edu.board.enums.ReadType;
import com.ktdsuniversity.edu.board.vo.BoardVO;
import com.ktdsuniversity.edu.board.vo.SearchResultVO;
import com.ktdsuniversity.edu.board.vo.request.UpdateVO;
import com.ktdsuniversity.edu.board.vo.request.WriteVO;
import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.helpers.MultipartFileHandler;
import com.ktdsuniversity.edu.files.vo.request.UploadVO;

@Service
public class BoardServiceImpl implements BoardService {

	/*
	 * 빈 컨테이너에 들어있는 객체중 타입이 일치하는 객체를 할당 받는다.
	 * 
	 * */
	
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private MultipartFileHandler multipartFileHandler;
	
	@Autowired
	private FilesDao filesDao;
	
	@Override
	public SearchResultVO findAllBoard() {
		SearchResultVO result = new SearchResultVO();
		//게시글 개수 조회 ==>1.
		int count =this.boardDao.selectBoardCount();
		result.setCount(count);
		if(count ==0) {
			return result;
		}
		// 게시글 목록 조회 ==>[BoardVO]
		List<BoardVO> list = this.boardDao.selectBoardList();
		
		
		result.setResult(list);
		
		return result;
	}
	
	@Override
	public boolean createNewBoard(WriteVO writeVO) {
		// dao => insert 요청
		// mybatis 는 insert, updatem delete를 수행했을떄 
		// 영향을 받은 row의 수를 반환시킨다.
		// 예 insert ==> insert 된 row의 개수 반환.
		// update ==> update 된 row의 개수 반환.
		// delete ==> delete 된 row의 새수 반환.
		
		int insertCount = this.boardDao.insertNewBoard(writeVO);
		
		// 첨부파일 업로드
		List<MultipartFile> attachFiles= writeVO.getAttachFile();
		this.multipartFileHandler.upload(attachFiles, writeVO.getId());
		
		System.out.println("생성된 게시글 믓개 ?"+insertCount);
		return insertCount == 1;
	}

	@Override
	public BoardVO findBoardByArticleId(String articleId, ReadType readType) {
	    if (readType == ReadType.VIEW) {
	        int updateCount = this.boardDao.updateViewCntIncreaseById(articleId);
	        // 1. 게시글 조회
	        BoardVO board = this.boardDao.selectBoardById(articleId);
	        // 2. 조회수 증가
	        System.out.println("조회수가 증가된 게시글의 수: " + updateCount);
	        if (updateCount == 0) {
	            return null;
	        }
	        return board;
	    }
	    return this.boardDao.selectBoardById(articleId);
	}


	@Override
	public boolean deletePosts(String id) {
		int deletePostsById = this.boardDao.deletePostsByArticleId(id);
	
		//삭제하려는 게시글에 첨부된 파일 목록을 가져온다.
	List<String> filePaths = this.filesDao.selectFilePathByFileGroupId(id);
	if(filePaths !=null && filePaths.size()>0) {	
		//파일 목록이 존재하면, 모든 파일들을 제거한다.
		for(String path: filePaths) {
			new File(path).delete();
		}
		// 파일 목록을 제거한 이후에 "FILES" 테이블에서 해당 파일 정보를 모두 삭제한다.
		int deleteFileCount = this.filesDao.deleteFileByFileGroupId(id);
		System.out.println("파일 삭제 개수?" + deleteFileCount);
	}
		
		
		return deletePostsById == 1;
}

	@Override
	public boolean updateBoardByArticleId(UpdateVO updateVO) {
		int updateCount = this.boardDao.updateBoardById(updateVO);
		
		// 선택한 파일들만 삭제.
		if(updateVO.getDeleteFileNum() !=null && updateVO.getDeleteFileNum().size()>0) {
			// 선택한 파일들의 정보를 조회 --> 파일의 경로 --> 실제파일을 제거.
			List<String> deleteTargets = this.filesDao.selectFilePathByFileGroupIdAndFileNums(updateVO);
			// 선택한 파일들을 FILES 테이블에서 제거.
			for(String target:deleteTargets) {
				new File(target).delete();
			}
			// 선택한 파일들을 FILES 테이블 제거.
			int deleteCount = this.filesDao.deleteFilesByFileGroupIdAndFileNums(updateVO);
			System.out.println(deleteCount);	
		}
		
		
		
		List<MultipartFile> attachFiles = updateVO.getAttachFile();
		this.multipartFileHandler.upload(attachFiles, updateVO.getId());
		return updateCount == 1;
	}



}
