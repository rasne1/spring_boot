<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>영화 리스트 조회</title>
    <link rel="stylesheet" type="text/css" href="/css/movie.css">
  </head>
  <body>
    <h2>영화 리스트</h2>
    <div>
      <div>번호:${searchResult[0].movieId}</div>
      <div>제목:${searchResult[0].title}</div>
      <div>포스터url:${searchResult[0].posterUrl}</div>
      <div>관람등급:${searchResult[0].movieRating}</div>
      <div>개봉일:${searchResult[0].openDate}</div>
      <div>개봉국가:${searchResult[0].openCountry}</div>
      <div>러닝타임:${searchResult[0].runningTime}</div>
      <div>소개글:${searchResult[0].introduce}</div>
      <div>시놉시스:${searchResult[0].synopsis}</div>
      <div>원제:${searchResult[0].originalTitle}</div>
      <div>개봉상태:${searchResult[0].state}</div>
      <div>언어:${searchResult[0].language}</div>
      <div>제작비:${searchResult[0].budget}</div>
      <div>수익:${searchResult[0].profit}</div>
      <div>${searchCount}</div>
    </div>
  </body>
</html>
