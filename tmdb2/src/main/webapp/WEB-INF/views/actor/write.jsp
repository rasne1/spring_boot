<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배우 등록</title>
<script type="text/javascript" src="/js/jquery-4.0.0.slim.min.js"></script>
<script type="text/javascript" src="/js/actor-write.js"></script>
<link rel="stylesheet" type="text/css" href="/css/actor-write.css">
</head>
<body>
    <h1>배우 등록</h1>
    <form:form
      modelAttribute="actorWriteVO"
      method="post"
      action="/actor"
      enctype="multipart/form-data"
    >
    <div class="grid write">
        <label for="actorName">이름</label>
        <div class="input-div">
        <input
          type="text"
          id="actorName"
          name="actorName"
          placeholder="이름을 입력하세요."
          value="${inputData.actorName}"
        />
        <form:errors path="actorName" cssClass="validation-error" element="div"/>
        </div>
        

		</div>
        <label for="attach-files">배우 프로필 사진</label>
        <div id="attach-files" class="attach-files">
          <input type="file" name="attachFiles"/>
        </div>
    
    

        <div class="btn-group" >
          <div class="right-align">
            <input type="submit" value="저장" />
		 </div>
		</div>
</form:form>		
</body>
</html>