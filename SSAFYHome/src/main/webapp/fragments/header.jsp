<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/common.css">

<div class="sidebar">
	<div class="profile">
		<c:choose>
			<c:when test="${not empty sessionScope.loginUser && not empty sessionScope.loginUser.profileBase64}">
				<img src="data:image/png;base64,${sessionScope.loginUser.profileBase64}" alt="회원 프로필" />
			</c:when>
			<c:otherwise>
				<img src="${pageContext.request.contextPath}/assets/img/avatar-null.png" alt="기본 프로필" />
			</c:otherwise>
		</c:choose>

		<span>
			<c:choose>
				<c:when test="${not empty sessionScope.loginUser}">
					${sessionScope.loginUser.name}
				</c:when>
				<c:otherwise>
					게스트
				</c:otherwise>
			</c:choose>
		</span>
	</div>

	<nav class="nav-links">
		<a href="index.jsp" class="nav-item"> <img
			src="${pageContext.request.contextPath}/assets/img/home-icon.png"
			alt="Home" /> 메인 페이지
		</a>

		<c:if test="${not empty sessionScope.loginUser}">
			<a href="member?act=detail&email=${sessionScope.loginUser.email}"
				class="nav-item"> <img
				src="${pageContext.request.contextPath}/assets/img/mypage-icon.png"
				alt="My Page" /> 마이페이지
			</a>
		</c:if>

		<c:if
			test="${not empty sessionScope.loginUser && sessionScope.loginUser.role eq 'ADMIN'}">
			<a href="member?act=list" class="nav-item"> <img
				src="${pageContext.request.contextPath}/assets/img/members-icon.png"
				alt="Members" /> 회원 목록 조회
			</a>
		</c:if>
	</nav>


	<div class="sidebar-footer">
		<c:choose>
			<c:when test="${not empty sessionScope.loginUser}">
				<a href="member?act=logout" class="logout-btn">
					<img src="${pageContext.request.contextPath}/assets/img/logout-icon.png" alt="Logout" />
					로그아웃
				</a>
			</c:when>
			<c:otherwise>
				<a href="member?act=login-form" class="logout-btn">
					<img src="${pageContext.request.contextPath}/assets/img/login-icon.png" alt="Login" />
					로그인
				</a>
				<a href="member?act=regist-member-form" class="logout-btn">
					<img src="${pageContext.request.contextPath}/assets/img/regist-icon.png" alt="Join" />
					회원가입
				</a>
			</c:otherwise>
		</c:choose>
	</div>
</div>
