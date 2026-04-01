<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>영화 등록</title>
<link rel="stylesheet" type="text/css" href="/css/movie.css">
</head>
<body>
    <h1>영화 등록</h1>
    <form method="post" action="/write">
      <div class="grid write">
        <label for="title">제목</label>
        <input
          type="text"
          id="title"
          name="title"
          placeholder="제목을 입력하세요."
        />
        
        <label for="posterUrl">포스터</label>
        <input
          type="text"
          id="posterUrl"
          name="posterUrl"
          placeholder="포스터 url을 입력하세요."
        />

        <label for="movieRating">관람등급</label>
        <input
          id="movieRating"
          type="text"
          name="movieRating"
          placeholder="관람등급을 입력하세요"
        />
        
        <label for="openDate">개봉일</label>
        <input
          id="openDate"
          type="date"
          name="openDate"
          placeholder="개봉일을 입력하세요"
        />
        
        <label for="openCountry">개봉국가</label>
        <input
          id="openCountry"
          type="text"
          name="openCountry"
          placeholder="개봉국가를 입력하세요"
        />

        <label for="runningTime">러닝타임</label>
        <input
          id="runningTime"
          type="int"
          name="runningTime"
          placeholder="러닝타임을 입력하세요"
        />
        
        <label for="introduce">소개글</label>
        <textarea
          id="introduce"
          name="introduce"
          placeholder="소개글을 입력하세요"
        ></textarea>
        
        <label for="synopsis">줄거리</label>
        <textarea
          id="synopsis"
          name="synopsis"
          placeholder="줄거리를 입력하세요"
        ></textarea>
        
        <label for="originalTitle">원제</label>
        <input
          id="originalTitle"
          type="text"
          name="originalTitle"
          placeholder="원제를 입력하세요"
        />
        
        <label for="state">개봉상태</label>
        <input
          id="state"
          type="text"
          name="state"
          placeholder="개봉상태를 입력하세요"
        />
        
        <label for="language">언어</label>
        <input
          id="language"
          type="text"
          name="language"
          placeholder="언어를 입력하세요"
        />
        
       

        
        <div class="btn-group">
          <div class="right-align">
            <input type="submit" value="저장" />

</body>
</html>