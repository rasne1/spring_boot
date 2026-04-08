<%-- <%@ Directive %> --%>
<%-- Page Directive --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- JSTL을 사용하기 위해서는 taglib directive 필요 --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<jsp:include page="/WEB-INF/views/templates/header.jsp">
<jsp:param value="회원 목록" name="title"/>
</jsp:include>
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
                        <td>
                        <td>${members.email}</td>
                        <td>
                            <a href="/members/view/${members.email}">${members.name}</a>
                        </td>
                        <td>${members.email}</td>
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
             <!-- searchResult가 존재하지 않으면, "검색된 데이터가 없습니다"를 보여주고-->
             <!-- searchResult가 존재하면, 반복하여 데이터를 보여주고 -->
            
        </tbody>
        <a href="/regist">새로운 회원 가입</a>
    </table>
<jsp:include page="/WEB-INF/views/templates/footer.jsp"></jsp:include>
