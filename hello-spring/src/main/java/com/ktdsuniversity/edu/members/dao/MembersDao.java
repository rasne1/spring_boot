package com.ktdsuniversity.edu.members.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.MembersVO;

@Mapper
public interface MembersDao {
	
	int insertNewMembers(MembersVO membersVO);

	int updateViewCntIncreaseById(String id);

	List<MembersVO> selectMemberList();

	int selectMemberCount();

	MembersVO selectMembersByEmail(String email);

	int updateMemberByEmail(MemberUpdateVO memberUpdateVO);



}
