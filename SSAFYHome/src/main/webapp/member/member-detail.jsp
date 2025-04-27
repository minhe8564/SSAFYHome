<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/member.css">
</head>
<body>

	<%@ include file="/fragments/header.jsp"%>

	<div class="container">

		<form action="member" method="post" class="form-box" enctype="multipart/form-data">
			<input type="hidden" name="mno" value="${member.mno}" />

			<div class="profile-preview">
				<label>프로필 사진</label>
				<c:choose>
					<c:when test="${not empty member.profileBase64}">
						<img id="preview" src="data:image/png;base64,${member.profileBase64}" alt="프로필 이미지" />
					</c:when>
					<c:otherwise>
						<img id="preview" src="${pageContext.request.contextPath}/assets/img/avatar-null.png" alt="기본 프로필" />
					</c:otherwise>
				</c:choose>
			</div>

			<input type="text" name="name" value="${member.name}" required /> 
			<input type="email" name="email" value="${member.email}" required />
			<input type="password" value="${member.password}" readonly />
			<input type="password" name="newPassword" placeholder="새 비밀번호 입력 (미입력 시 기존 유지)" />

			<c:choose>
			  <c:when test="${sessionScope.loginUser.role eq 'ADMIN'}">
			    <select name="role">
			      <option value="USER" ${member.role eq 'USER' ? 'selected' : ''}>일반 사용자</option>
			      <option value="ADMIN" ${member.role eq 'ADMIN' ? 'selected' : ''}>관리자</option>
			    </select>
			  </c:when>
			  <c:otherwise>
			    <input type="text" name="role" value="${not empty member.role ? member.role : ''}" placeholder="일반 사용자" readonly />
			  </c:otherwise>
			</c:choose>

			<input type="file" name="profile" accept="image/*" onchange="readURL(this);" />

			<div class="btn-box">
				<button type="submit" name="act" value="update" class="btn btn-primary">수정하기</button>
				<button type="submit" name="act" value="delete" class="btn btn-danger">삭제하기</button>
			</div>
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