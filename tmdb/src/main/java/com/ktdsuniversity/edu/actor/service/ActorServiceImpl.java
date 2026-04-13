package com.ktdsuniversity.edu.actor.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ktdsuniversity.edu.actor.dao.ActorDao;
import com.ktdsuniversity.edu.actor.vo.request.ActorWriteVO;
import com.ktdsuniversity.edu.files.dao.FilesDao;
import com.ktdsuniversity.edu.files.vo.request.UploadVO;

@Service
public class ActorServiceImpl implements ActorService {

	@Autowired
	private ActorDao actorDao;
	
	@Autowired
	private FilesDao filesDao;

	@Transactional
	@Override
	public boolean createNewActor(ActorWriteVO actorWriteVO) {
		
		int insertCount = this.actorDao.insertNewActor(actorWriteVO);
		
		List<MultipartFile> attachFiles = actorWriteVO.getAttachFiles();

		if (attachFiles != null && attachFiles.size() > 0) {
			for (int i = 0; i < attachFiles.size(); i++) {
				
				
				File storeFile = new File("c://uploadfiles", attachFiles.get(i).getOriginalFilename());
				if (!storeFile.getParentFile().exists()) {
					storeFile.getParentFile().mkdirs();
				}
				try {
					attachFiles.get(i).transferTo(storeFile);
					UploadVO uploadVO = new UploadVO();
					String filename = attachFiles.get(i).getOriginalFilename();
					String ext = filename.substring(filename.lastIndexOf(".") + 1);
					uploadVO.setFileGroupId(actorWriteVO.getActorId());
					uploadVO.setObfuscateName(filename);
					uploadVO.setDisplayName(filename);
					uploadVO.setExtendName(ext);
					uploadVO.setFileLength(storeFile.length());
					uploadVO.setFilePath(storeFile.getAbsolutePath());

				this.filesDao.insertAttachFile(uploadVO);


				} catch (IllegalStateException | IOException e) {

					throw new RuntimeException("업로드 실패", e);
				}

			}

		}
		
		return insertCount == 1;

	}

}
