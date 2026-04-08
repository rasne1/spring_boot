package com.ktdsuniversity.edu.members.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ktdsuniversity.edu.board.service.BoardServiceImpl;
import com.ktdsuniversity.edu.exceptions.HelloSpringException;
import com.ktdsuniversity.edu.members.dao.MembersDao;
import com.ktdsuniversity.edu.members.enums.ReadType;
import com.ktdsuniversity.edu.members.helpers.SHA256Util;
import com.ktdsuniversity.edu.members.vo.MembersVO;
import com.ktdsuniversity.edu.members.vo.request.LoginVO;
import com.ktdsuniversity.edu.members.vo.request.MemberUpdateVO;
import com.ktdsuniversity.edu.members.vo.request.RegistVO;
import com.ktdsuniversity.edu.members.vo.response.SearchResultVO;

import jakarta.validation.Valid;

@Service
public class MembersServiceImpl implements MembersService {

	private static final Logger logger = LoggerFactory.getLogger(MembersServiceImpl.class);
	
	@Autowired
	private MembersDao membersDao;

	@Transactional
	@Override
	public boolean createNewMember(RegistVO registVO) {

		MembersVO membersVO = this.membersDao.selectMembersByEmail(registVO.getEmail());
		if (membersVO != null) {
			//throw new IllegalArgumentException(registVO.getEmail() + "은 이미 사용중입니다.");
			throw new HelloSpringException("사용중 이메일","members/regist", registVO);
		}

		// 암호화를 위한 비밀키 생성.
		String newSalt = SHA256Util.generateSalt();
		String usersPassword = registVO.getPassword();
		// 사용자가 입력한 비밀번호를 newSalt를 이용해 암호화
		// 비밀번호와 newSalt의 값이 일치하면, 항상 같은 값의 암호화 결과가 생성된다.
		usersPassword = SHA256Util.getEncrypt(usersPassword, newSalt);

		// 비밀키 저장.
		registVO.setSalt(newSalt);
		// 암호화된 비밀번호 저장.
		registVO.setPassword(usersPassword);

		int insertCount = this.membersDao.insertNewMembers(registVO);
		return insertCount == 1;

	}

	
	@Override
	public SearchResultVO findAllMemberList() {
		SearchResultVO result = new SearchResultVO();
		int count = this.membersDao.selectMemberCount();
		result.setCount(count);
		if (count == 0) {
			return result;
		}

		List<RegistVO> list = this.membersDao.selectMemberList();
		logger.debug("{} 유우지태",list);
		//System.out.println(list + "유우지태");
		result.setResult(list);

		return result;
	}

	@Override
	public MembersVO findMemberByEmail(String email) {
		MembersVO searchResult = this.membersDao.selectMembersByEmail(email);

		return searchResult;
	}

	@Transactional
	@Override
	public boolean updateMemberByEmail(MemberUpdateVO memberUpdateVO) {
		int updateCount = this.membersDao.updateMemberByEmail(memberUpdateVO);
		return updateCount == 1;
	}

	@Transactional(noRollbackFor = HelloSpringException.class)
	@Override
	public MembersVO findMemberByEmailAndPassword(LoginVO loginVO) {
		// 1. Email을 이용해 회원 정보 조회하기 (selectMemberByEmail)
		MembersVO searchResult = this.membersDao.selectMembersByEmail(loginVO.getEmail());
		// 2. 조회된 결과가 없다면 "이메일 또는 비밀번호가 잘못되었습니다." 예회 던지기
		if (searchResult == null) {
			//throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
			throw new HelloSpringException("이메일이나 비밀번호 오류","members/login", loginVO);
//		    IllegalArgumentException
		}

		if (searchResult.getBlockYn().equals("Y")) {

			String latestLoginFailDate = searchResult.getLatestLoginFailDate();

			DateTimeFormatter dateTimePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			LocalDateTime latestBlockDateTime = LocalDateTime.parse(latestLoginFailDate, dateTimePattern);

			if (latestBlockDateTime.isAfter(LocalDateTime.now().minusMinutes(120))) {
				//throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
				throw new HelloSpringException("이메일이나 비밀번호 오류","members/login", loginVO);
			}

		}

		// 3. 조회된 결과가 있다면 사용자가 전송한 비밀번호와 조회된 회원의 salt를 이용해 SHA 암호화 하기
		String inputpassword = loginVO.getPassword();
		String salt = searchResult.getSalt();
		String userpassword = SHA256Util.getEncrypt(inputpassword, salt);

		// 4. 3에서 암호화 한 비밀번호와 1에서 조회한 비밀번호가 일치하는지 확인하기.
		if (!userpassword.equals(searchResult.getPassword())) {
			// 5. 비밀번호가 일치하지 않는다면 "이메일 또는 비밀번호가 잘못되었습니다." 예외 던지기
			// 해당 이메일의 로그인 실패 횟수를 1증가시키고
			// 최근 로그인 실패 날짜를 현재 날짜와 시간으로 변경한다.
			this.membersDao.updateIncreaseLoginFailCount(loginVO.getEmail());

			// 최근 로그인 실패 횟수가 5 이상이라면 block-yn을 y로 변경한다.

			this.membersDao.updateBlockYn(loginVO.getEmail());

			//throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
			throw new HelloSpringException("이메일이나 비밀번호 오류","members/login", loginVO);
		}

		// 로그인 성공처리
		// 1.login_fail_count를 0으로 초기화
		// 2.latest_login_ip 를 현재 아이피로 변경.
		// 3.login_date를 현재 시간으로 변경
		// 4. block_yn을 'N'으로 변경.
		this.membersDao.updateSuccessLogin(loginVO);

		// 6. 비밀번호가 일치하면 1에서 조회한 결과를 반환.
		return searchResult;
	}

	@Transactional
	@Override
	public boolean deletMemberInfo(String memberEmail) {
	int deletSuccess =this.membersDao.deletByEmail(memberEmail);
		
		return deletSuccess == 1;
	
	}




}
