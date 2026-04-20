package com.ktdsuniversity.edu.security.providers;

import java.time.Duration;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/*
 * 사용자의 정보를 이용해 인증 객체를 생성하고 검증하는 클래스.
 * Spring Security AuthenticationProvider 와는 무관.
 * 사용목적 : API를 호출할 때 인증수단으로 사용하기 위해.
 * */



public class JsonWebTokenAuthenticationProvider {
	
	
	private String secretKey;
	private String issuer;
	
	public JsonWebTokenAuthenticationProvider(String secretKey, String issuer) {
		this.issuer = issuer;
		this.secretKey = secretKey;
		
	}
	
	/**
	 * 사용자가 요청 할때마다 Request Header[Authorization] 에 전달한
	 * JsonWebToken을 가져와 복호화 시킨다.
	 * 복호화 된 결과에서 사용자의 이메일(identify)을 추출하여 반환시킨다.
	 * 
	 * @param jsonWebToken 사용자가 전달한 토큰
	 * @return jsonWebToken에서 추출한 사용자의 이메일
	 */
	

	public String decryptJsonWebToken(String jsonWebToken) {
		
		// 암/복호화 키생성
		// TODO appliction.yml 에서 가져오기
		SecretKey signKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());
		
		
		Claims claims = Jwts.parser() // jsonWebToken을 분석하기 위한 선언.
				 		   .verifyWith(signKey) // jsonWebToken을 복호화 하기 위한 비밀키 지정
				 		   .requireIssuer(this.issuer) //사용자가 전달한 jsonWebToken이 hello-spring 시스템에서 만든것인지 확인한다.
						   .build() // jsonWebToken을 복호화 시작
						   .parseSignedClaims(jsonWebToken) //사용자가 전달한 JsonWebToken을 복호화 한다.
						   .getPayload(); // 복호화 된 결과에서 claim들만 모아 반환시킨다. (Map의 형태)
		
		// 사용자가 전달한 jsonWebToken을 복호화 한뒤 identity 값을 추출한다.
		String email = claims.get("identify", String.class);
		
		return email;
	}
	
	
	
	/*
	 * 사용자의 이메일을 이용해 인증용 JWT를 생성
	 * 
	 * @Param email 사용자의 이메일
	 * @Param expiredAt JWT의 유효기간 (지금부터 ~ 분 (시간,일,월,연) 까지 유효)
	 * @return email 과 expiredAt으로 생성한 JsonWebToken
	 * 
	 * */
	
	
	public String makeJsonWebToken(String email, Duration expiredAt) {
		
		//JsonWebToken이 발생되는 날짜와 시간을 생성.
		Date issueDate= new Date();
		//JsonWebToken이 만료되는 날짜와 시간을 생성.
		//발행 날짜 시간 + expiaredAt
		Date expirationDate = new Date( issueDate.getTime() + expiredAt.toMillis());
		
		// 암/복호화 키생성
		// appliction.yml 에서 가져오기
		SecretKey signKey = Keys.hmacShaKeyFor(this.secretKey.getBytes());
		
		String JsonWebToken = Jwts.builder()
								//JsonWebToken을 발생한 시스템의 이름
								// TODO appliction.yml 에서 가져오기
								  .issuer(this.issuer)
								//JsonWebToken의 이름
								  .subject(email + "_token")
								// JsonWebToken에 포함되어야 할 회원의 정보들
								  .claim("identify", email)
								// JsonWebToken을 발행한 시간
								  .issuedAt(issueDate)
								// JsonWebToken이 만료되는 시간.
								  .expiration(expirationDate)
								// 평문으로 구성된 JsonWebToken을 암호화 또는 복호화 시킬떄 사용할 키
								  .signWith(signKey)
				 				//Jwts에 제공된 데이터를 이용해 String Type의 Token을 생성.
								.compact();
		
		return JsonWebToken;
	}
	
	public static void main(String[] args) {
		
		JsonWebTokenAuthenticationProvider jwtProvider = new JsonWebTokenAuthenticationProvider("25xwkmldiwmxotfkehwhepVirmgldk58wEowpssxkwh","hello-spring");
		String jwt = jwtProvider.makeJsonWebToken("test@gmail.conm", Duration.ofHours(3));
		System.out.println(jwt);
		
		// 복호화 진행
		String email = jwtProvider.decryptJsonWebToken(jwt);
		System.out.println(email);
		
	}
}
