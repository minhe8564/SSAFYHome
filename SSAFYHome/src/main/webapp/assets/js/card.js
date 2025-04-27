/**
 * 거래 목록 데이터를 받아서 카드 UI로 화면에 출력하는 함수
 * @param {Array} deals - 거래 정보 배열
 */
function renderDealCards(deals) {
  const container = document.querySelector(".deal-list");
  container.innerHTML = ""; // 기존 내용 비우기

  if (!deals || deals.length === 0) {
    container.innerHTML = "<p>조회된 거래 정보가 없습니다.</p>";
    return;
  }

  deals.forEach((deal) => {
    const card = document.createElement("div");
    card.className = "deal-card";

    const title = `${deal.aptNm} (${deal.floor}층)`;
    const address = `${deal.umdNm} ${deal.jibun}`;
    const price = `${deal.dealAmount} 만원`;
    const area = `${parseFloat(deal.excluUseAr).toFixed(1)} ㎡`;
    const date = `${deal.dealYear}년 ${deal.dealMonth}월 ${deal.dealDay}일`;

    card.innerHTML = `
      <h3>${title}</h3>
      <p><strong>거래일:</strong> ${date}</p>
      <p><strong>거래금액:</strong> ${price}</p>
      <p><strong>면적:</strong> ${area}</p>
      <p><strong>주소:</strong> ${address}</p>
    `;

    container.appendChild(card);
  });
}

// export 함수 등록
window.renderDealCards = renderDealCards;