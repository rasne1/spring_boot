<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 상세 조회</title>
    <link rel="stylesheet" type="text/css" href="/css/movie.css">
  </head>
  <body>
    <h2>영화 정보</h2>
    <div class="grid">
      <div>번호:${movie.movieId}</div>
      <div>제목:${movie.title}</div>
      <div>포스터url:${movie.posterUrl}</div>
      <div>관람등급:${movie.movieRating}</div>
      <div>개봉일:${movie.openDate}</div>
      <div>개봉국가:${movie.openCountry}</div>
      <div>러닝타임:${movie.runningTime}</div>
      <div>소개글:${movie.introduce}</div>
      <div>시놉시스:${movie.synopsis}</div>
      <div>원제:${movie.originalTitle}</div>
      <div>개봉상태:${movie.state}</div>
      <div>언어:${movie.language}</div>
      <div>제작비:${movie.budget}</div>
      <div>수익:${movie.profit}</div>
      <div>첨부파일
         <ul class="verical-list">
          <c:forEach items="${movie.files}" var="file">
            <li>
              <a href="/file/${file.fileGroupId}/${file.fileNum}">
                ${file.displayName}
              </a>
            </li>
            <img src="/file/${file.fileGroupId}/${file.fileNum}"/>
          </c:forEach>
        </ul> 
      </div>
    </div>
  </body>
</html>