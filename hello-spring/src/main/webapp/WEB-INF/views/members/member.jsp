<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>회원가입</title>
    <script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
    <script type="text/javascript" src="/js/members.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css" />
  </head>
  <body>
    <h1>회원가입</h1>
    <form:form
      modelAttribute="membersVO"
      method="post"
      action="/regist"
      enctype="multipart/form-data"
    >
      <div class="grid">
        <label for="name">아이디</label>
        <input
          type="text"
          id="name"
          name="name"
          placeholder="아이디를 입력하하세요."
        />
        <form:errors path="name" cssclass="validation-error" element="div"/>
        <label for="password">비밀번호</label>
        <input
          type="text"
          id="password"
          name="password"
          placeholder="비밀번호를 입력하세요."
        />
        <form:errors path="password" cssclass="validation-error"  element="div"/>
        <label for="email">이메일</label>
        <input
          type="text"
          id="email"
          name="email"
          placeholder="이메일을 입력하세요."        />
          
          <!-- value="${inputData.email}" -->
        <form:errors path="email" cssclass="validation-error"  element="div"/>

        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />
          </div>
        </div>
      </div>
    </form:form>
  </body>
</html>

