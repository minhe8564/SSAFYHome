<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 목록</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/member.css">
</head>
<body>
    <%@ include file="/fragments/header.jsp" %>

    <div class="container">
        <h2>👤 가입된 회원 목록</h2>
        <table class="member-table">
            <thead>
                <tr>
                    <th>No</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>관리</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="m" items="${members}">
                    <tr>
                        <td>${m.mno}</td>
                        <td>${m.name}</td>
                        <td>${m.email}</td>
                        <td>
                            <a href="member?act=detail&email=${m.email}" class="btn btn-primary">상세</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <%@ include file="/fragments/footer.jsp" %>
</body>
</html>
