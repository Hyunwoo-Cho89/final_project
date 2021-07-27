<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
	<c:set var="contextPath" value="<%=request.getContextPath()%>" />
	<c:set var="position" value="${sessionScope.position }" />

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark"
		style="padding: 5;">
		<!-- Brand -->
		<a href="${contextPath }/main"><img class="navbar-brand rounded-circle"
			src="/root/resources/img/5.jpg" width=80px; height=80px;></a>

		<!-- Links -->
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<ul class="navbar-nav">
			<c:choose>
				<c:when test="${position == '학생' }">
					<li class="nav-item"><a class="nav-link" href="/root/student/studentMain">마이페이지</a></li>
				</c:when>
				<c:when test="${position == '교수' }">
					<li class="nav-item"><a class="nav-link" href="professorMain">종합시스템</a></li>
				</c:when>
				<c:when test="${position == '관리자' }">
					<li class="nav-item"><a class="nav-link"  href="adminMain">종한관리시스템</a></li>
				</c:when>
				<c:otherwise>
					<li class="nav-item"><a class="nav-link" href="/root/">비회원으로 사용 중입니다. 로그인을 진행하려면 클릭해 주세요.</a></li>
				</c:otherwise>
			</c:choose>
			</ul>
		</nav>
		</nav>	

		



</body>
</html>