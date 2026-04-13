<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/regist.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/movie.css" />
  </head>
  <body>
    <h1>회원가입</h1>
    <form:form
      modelAttribute="registVO"
      method="post"
      action="/regist"
      enctype="multipart/form-data"
    >
      <div class="grid">
        <label for="email">이메일</label>
        <div class="input-div">
          <input
            type="text"
            id="email"
            name="email"
            placeholder="이메일을 입력하세요."
            value="${inputData.email}"
          />

          <form:errors path="email" cssClass="validation-error" element="div" />
        </div>

        <label for="name">아이디</label>
        <div class="input-div">
          <input
            type="text"
            id="name"
            name="name"
            placeholder="이름을 입력하세요."
            value="${inputData.name}"
          />
          <form:errors path="name" cssClass="validation-error" element="div" />
        </div>
        <label for="password">비밀번호</label>
        <div class="input-div">
          <input
            type="password"
            id="password"
            name="password"
            placeholder="비밀번호를 입력하세요."
          />
          <form:errors
            path="password"
            cssClass="validation-error"
            element="div"
          />
        </div>
        <label for="confirm-password">비밀번호 확인</label>
        <div class="input-div">
          <input
            type="password"
            id="confirm-password"
            name="confirm-password"
          />
        </div>

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>
