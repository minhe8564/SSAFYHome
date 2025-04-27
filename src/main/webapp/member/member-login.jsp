<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/member.css">
</head>
<body>
	<%@ include file="/fragments/header.jsp"%>

	<div class="container">

		<form action="member?act=login" method="post" class="form-box">
			<label for="name">이름</label> <input type="text" id="name" name="name"
				value="${cookie.saveName.value}" required /> <label for="password">비밀번호</label>
			<input type="password" id="password" name="password" required /> <label>
				<input type="checkbox" name="saveName"
				<c:if test="${not empty cookie.saveName.value}">checked</c:if> />
				이름 저장
			</label>

			<c:if test="${param.error == 1}">
				<div class="error-box">❌ 이름과 비밀번호를 다시 확인하세요.</div>
			</c:if>

			<button type="submit" class="btn btn-primary">로그인</button>
		</form>

		<%@ include file="/fragments/footer.jsp"%>
</body>
</html>
