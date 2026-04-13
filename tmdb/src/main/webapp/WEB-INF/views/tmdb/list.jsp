<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 리스트 조회</title>
    <link rel="stylesheet" type="text/css" href="/css/movie.css">
   </head>
  <body>
    <h1>게시글 목록</h1>
    <div>총 ${searchCount}개의 게시글이 검색되었습니다.</div>
       <table class="grid">
        <thead>
            <tr>
            <th>번호</th>
            <th>제목</th>
            <th>관람등급</th>
            <th>개봉일</th>
            <th>개봉상태</th>
            <th>언어</th>
            </tr>
        </thead>
        <tbody>
          <c:choose>
            <c:when test="${not empty searchResult}">
            <c:forEach items="${searchResult}" var="movie">
              <tr>
                  <td>${movie.movieId}</td>  
                  <td>
                    <a href="/view/${movie.movieId}">${movie.title}</a>
                  </td>
                  <td>${movie.movieRating}</td>
                  <td>${movie.openDate}</td>
                  <td>${movie.state}</td>
                  <td>${movie.language}</td>
              </tr>
            </c:forEach>
            </c:when>
            <c:otherwise>
                  <tr>
                    <td colspan="6">검색된 데이터가 없습니다.</td>
                  </tr>
            </c:otherwise>
          </c:choose>
        </tbody>
        <a href="/write">새로운 게시글 작성</a>
    </table>
    
  </body>
</html>
