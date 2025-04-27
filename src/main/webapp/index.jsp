<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Index Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
</head>
<body>
  <%@ include file="/fragments/header.jsp"%>
  <div class="container">
    <h1>📍 아파트 실거래가 조회</h1>

    <div class="form-box">
      <label>시도 선택</label>
      <select id="sido"></select>
      <label>구군 선택</label>
      <select id="gugun"></select>
      <label>읍면동 선택</label>
      <select id="dong"></select>
      <label>조회 년월</label>
      <input type="month" id="dealYmd" />
      <button class="btn btn-primary" id="btn_apt_search">조회</button>
    </div>

    <div id="map" class="map-box"></div>

    <div id="resultBox" class="result-box"></div>
  </div>

  <%@ include file="/fragments/footer.jsp"%>

  <script type="text/javascript" src="https://sgisapi.kostat.go.kr/OpenAPI3/auth/javascriptAuth?consumer_key=c661120d3532438fa1fb"></script>
  <script src="${pageContext.request.contextPath}/assets/js/keys.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/common.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/kostat.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/happyHouse.js"></script>
  <script src="${pageContext.request.contextPath}/assets/js/card.js"></script> <!-- 💡 여기 추가 -->

  <script>
    const init = async () => {
      updateSido();
      updateMap([
        {
          address: "서울특별시 강남구 테헤란로 212",
          utmk: await getCoords("서울특별시 강남구 테헤란로 212"),
          label: "멀티캠퍼스"
        },
      ]);
    };
    init();
  </script>
</body>
</html>
