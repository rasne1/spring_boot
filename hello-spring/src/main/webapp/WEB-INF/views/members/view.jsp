<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/templates/header.jsp">
<jsp:param value="회원 조회" name="title"/>
</jsp:include>
    <h1>회원 조회</h1>
    <div class="grid view">
      <span>이름</span>
      <div>${members.name}</div>

      <span>비밀번호</span>
      <div>${members.password}</div>

      <span>이메일</span>
      <div>${members.email}</div>

      <div class="btn-group">
        <div class="right-align">
            <a href="/members/update/${members.email}">수정</a>
            <a href="/delete-me">회원탈퇴</a>
            
        </div>
      </div>
    </div>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
