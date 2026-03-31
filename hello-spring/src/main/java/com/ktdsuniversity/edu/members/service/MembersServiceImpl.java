package com.ktdsuniversity.edu.members.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.enums.ReadType;
import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.MembersVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

@Service
public class MembersServiceImpl implements MembersService {
	
	@Autowired
	private MembersDao membersDao;

	@Override
	public boolean createNewMember(MembersVO membersVO) {
		int insertCount = membersDao.insertNewMembers(membersVO);
		return insertCount == 1;
	}

	@Override
	public SearchResultVO findAllMemberList() {
		SearchResultVO result = new SearchResultVO();
		int count = this.membersDao.selectMemberCount();
		result.setCount(count);
		if(count ==0) {
			return result;
		}
		
		List<MembersVO> list = this.membersDao.selectMemberList();
		System.out.println(list+"유우지태");
		result.setResult(list);
		
		return result;
	}

	@Override
	public MembersVO findMemberByEmail(String email) {
		MembersVO member = this.membersDao.selectMembersByEmail(email);
		
		return member;
	}

	@Override
	public boolean updateMemberByEmail(MemberUpdateVO memberUpdateVO) {
		int updateCount = this.membersDao.updateMemberByEmail(memberUpdateVO);
		return updateCount == 1;
	}

	


	
	

}
