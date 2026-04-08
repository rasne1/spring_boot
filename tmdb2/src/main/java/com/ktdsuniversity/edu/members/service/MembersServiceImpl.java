package com.ktdsuniversity.edu.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;

@Service
public class MembersServiceImpl implements MembersService {
	
	@Autowired
	private MembersDao membersDao;

	@Override
	public boolean createNewMember(RegistVO registVO) {
		
		MembersVO membersVO = this.membersDao.selectMembersByEmail(registVO.getEmail());
		if(membersVO != null) {
			throw new IllegalArgumentException(registVO.getEmail()+"은 사용중");
		}
		
		String newSalt = SHA256Util.generateSalt();
		
		String uesrPassword = registVO.getPassword();
		
		uesrPassword = SHA256Util.getEncrypt(uesrPassword, newSalt);
		
		registVO.setSalt(newSalt);
		
		registVO.setPassword(uesrPassword);
		
		
		int insertResult = this.membersDao.insertNewMembers(registVO);
		
		
		return insertResult == 1;
	}

	@Override
	public MembersVO findMembersByEmail(String email) {
		MembersVO searchResult = this.membersDao.selectMembersByEmail(email);
		return searchResult;
	}

}
