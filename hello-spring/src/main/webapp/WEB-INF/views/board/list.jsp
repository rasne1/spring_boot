<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>게시글 목록</title>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
  </head>
  <body>
    <h1>게시글 목록</h1>
    <div>총 ${searchCount}개의 게시글이 검색되었습니다.</div>
    <!-- HTML 주석: 브라우저 개발자 도구에서 노출되는 주석. -->
    <%-- JSP 주석: 브라우저 개발자 도구에서 노출되지 않는 주석. --%>
    <%--
    <% for (BoardVO board: searchResult) { %>
        <div><%=board.getId()%></div>
    <% } %>
     --%>
    <table class="grid">
      <colgroup>
        <col width="200"/>
        <col width="*"/>
        <col width="150"/>
        <col width="80"/>
        <col width="180"/>
        <col width="180"/>
      </colgroup>
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>이메일</th>
          <th>조회수</th>
          <th>등록일</th>
          <th>수정일</th>
        </tr>
      </thead>
      <tbody>
        <c:forEach items="${searchResult}" var="board">
          <tr>
            <td>${board.id}</td>
            <td class="ellipsis">${board.subject}</td>
            <td>${board.email}</td>
            <td>${board.viewCnt}</td>
            <td>${board.crtDt}</td>
            <td>${board.mdfyDt}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
  </body>
</html>
