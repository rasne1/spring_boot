package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.enums.ReadType;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.validation.Valid;

public interface MembersService {

	boolean createNewMember(RegistVO membersVO);

	SearchResultVO findAllMemberList();

	MembersVO findMemberByEmail(String email);

	boolean updateMemberByEmail(MemberUpdateVO memberUpdateVO);

	MembersVO findMemberByEmailAndPassword(LoginVO loginVO);

	boolean deletMemberInfo(String memberEmail);


	
	

}
