package com.ktdsuniversity.edu.members.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;

@Mapper
public interface MembersDao {
	
	int insertNewMembers(RegistVO registVO);

	int updateViewCntIncreaseById(String id);

	List<RegistVO> selectMemberList();

	int selectMemberCount();

	MembersVO selectMembersByEmail(String email);

	int updateMemberByEmail(MemberUpdateVO memberUpdateVO);

	int updateIncreaseLoginFailCount(String email);

	int updateBlockYn(String email);

	int updateSuccessLogin(LoginVO loginVO);

	int deletByEmail(String memberEmail);

	



}
