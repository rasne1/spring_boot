<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" href="/css/join.css" />
  </head>
  <body>
    <h1>회원수정</h1>
    <form method="post" action="/members/update/${members.email}">
        <label for="name">이름</label>
        <input
          type="text"
          id="name"
          name="name"
          placeholder="이름을 입력하세요."
          value="${members.name}"
        />
        <label for="password">비밀번호</label>
        <input
          type="text"
          id="password"
          name="password"
          placeholder="비밀번호를 입력하세요."
          value="${members.password}"
        />

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form>
  </body>
</html>
