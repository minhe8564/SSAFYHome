<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/member.css">
</head>
<body>
	<%@ include file="/fragments/header.jsp"%>

	<div class="container">
		<form action="member?act=regist" method="post" class="form-box"
			enctype="multipart/form-data">

			<div class="profile-preview">
				<img id="preview"
					src="${pageContext.request.contextPath}/assets/img/avatar-null.png"
					alt="미리보기" />
			</div>

			<input type="text" name="name" placeholder="이름" required /> <input
				type="email" name="email" placeholder="이메일" required /> <input
				type="text" name="password" placeholder="비밀번호" required /> <input
				type="file" name="profile" accept="image/*"
				onchange="readURL(this);" /> <label class="checkbox-label">
			</label>
			
			<c:if test="${not empty error}">
				<div class="error-box">${error}</div>
			</c:if>

			<button type="submit" class="btn btn-primary">가입하기</button>
		</form>
	</div>

	<%@ include file="/fragments/footer.jsp"%>

	<script>
    function readURL(input) {
      const preview = document.getElementById("preview");
      if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function(e) {
          preview.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
      }
    }
  </script>
</body>
</html>
