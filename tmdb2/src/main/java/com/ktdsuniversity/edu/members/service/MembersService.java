package com.ktdsuniversity.edu.members.service;

import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;

public interface MembersService {

	boolean createNewMember(RegistVO registVO);

	MembersVO findMembersByEmail(String email);


	

}
