<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>회원 목록</title>
    <link rel="stylesheet" type="text/css" href="/css/hello-spring.css">
</head>
<body>

<h1>회원 목록</h1>
<div>총 ${searchCount}회원이 검색되었습니다.</div>

<table class="grid">
    <thead>
        <tr>
            <th>이메일</th>
            <th>이름</th>
            <th>비밀번호</th>
        </tr>
    </thead>

    <tbody>
        <c:choose>
            <c:when test="${not empty searchResult}">
                <c:forEach items="${searchResult}" var="members">
                    <tr>
                        <td>${members.email}</td>
                        <td>
                            <a href="/members/view/${members.email}">
                                ${members.name}
                            </a>
                        </td>
                        <td>${members.password}</td>
                    </tr>
                </c:forEach>
            </c:when>

            <c:otherwise>
                <tr>
                    <td colspan="3">검색된 데이터가 없습니다.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>
<ul class="page-navigator">
<c:forEach begin="${pagination.groupStartPageNo}" end="${pagination.groupEndPageNo}" step="1" var="page">
<a href="/members/view?pageNo=${page}">
${page+1}
</c:forEach>
</ul>
<div>
    <a href="/regist">새로운 회원 가입</a>
</div>

</body>
</html>
