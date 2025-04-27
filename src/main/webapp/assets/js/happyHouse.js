const sido = document.querySelector("#sido");
const gugun = document.querySelector("#gugun");
const dong = document.querySelector("#dong");

const updateSido = async function () {
  try {
    const json = await getFetch("https://api.vworld.kr/ned/data/admCodeList", {
      format: "json",
      numOfRows: 100,
      key: key_vworld,
      domain: "localhost",
    });
    const infos = json.admVOList.admVOList;
    infos.forEach((item) => {
      item.key = item.admCode;
      item.label = item.lowestAdmCodeNm;
    });
    updateSelect(sido, "시도", infos);
    updateSelect(gugun, "구군");
    updateSelect(dong, "읍면동");
  } catch (error) {
    console.log(error);
  }
};

sido.addEventListener("change", async function () {
  try {
    const json = await getFetch("https://api.vworld.kr/ned/data/admSiList", {
      admCode: sido.value,
      format: "json",
      numOfRows: 100,
      key: key_vworld,
      domain: "localhost",
    });
    const infos = json.admVOList.admVOList;
    infos.forEach((item) => {
      item.key = item.admCode;
      item.label = item.lowestAdmCodeNm;
    });
    updateSelect(gugun, "구군", infos);
    updateSelect(dong, "읍면동");
  } catch (error) {
    console.log(error);
  }
});

gugun.addEventListener("change", async function () {
  try {
    const json = await getFetch("https://api.vworld.kr/ned/data/admDongList", {
      admCode: gugun.value,
      format: "json",
      numOfRows: 100,
      key: key_vworld,
      domain: "localhost",
    });
    const infos = json.admVOList.admVOList;
    infos.forEach((item) => {
      item.key = item.admCode;
      item.label = item.lowestAdmCodeNm;
    });

    updateSelect(dong, "읍면동", infos);
  } catch (error) {
    console.log(error);
  }
});

dong.addEventListener("change", async function () {
  const sidoLabel = sido.selectedOptions[0].text;
  const gugunLabel = gugun.selectedOptions[0].text;
  const dongLabel = dong.selectedOptions[0].text;
  const address = `${sidoLabel} ${gugunLabel} ${dongLabel}`;
  console.log("최종 address: " + address);
  updateMap([{ address: address, utmk: await getCoords(address), label: dongLabel }]);
});

document.querySelector("#btn_apt_search").addEventListener("click", async () => {
  const LAWD_CD = gugun.value;
  const DEAL_YMD = document.querySelector("#dealYmd").value;
  console.log(LAWD_CD, DEAL_YMD);

  const data = await getFetch(
    "https://apis.data.go.kr/1613000/RTMSDataSvcAptTrade/getRTMSDataSvcAptTrade",
    {
      LAWD_CD: LAWD_CD,
      DEAL_YMD: DEAL_YMD.replace("-", ""),
      serviceKey: key_data,
    },
    true
  );
  const xml = new DOMParser().parseFromString(data, "text/xml");
  const items = xml.getElementsByTagName("item");

  const infos = [];
  const sidoLabel = sido.selectedOptions[0].text;
  const gugunLabel = gugun.selectedOptions[0].text;

  for (let item of items) {
    const dongText = item.getElementsByTagName("umdNm")[0].textContent;
    const jibun = item.getElementsByTagName("jibun")[0].textContent;
    const aptNm = item.getElementsByTagName("aptNm")[0].textContent;
    const area = item.getElementsByTagName("excluUseAr")[0].textContent;
    const floor = item.getElementsByTagName("floor")[0].textContent;
    const amount = item.getElementsByTagName("dealAmount")[0].textContent.trim();
    const address = `${sidoLabel} ${gugunLabel} ${dongText} ${jibun} ${aptNm}`;
    const utmk = await getCoords(address);

    infos.push({
      address,
      label: aptNm,
      area,
      floor,
      amount,
      utmk
    });
  }

  updateMap(infos);
  renderResultCards(infos);
});

const renderResultCards = (infos) => {
  const container = document.getElementById("resultBox");
  container.innerHTML = "";
  infos.forEach((info) => {
    const card = document.createElement("div");
    card.className = "deal-card";
    card.innerHTML = `
      <h3>${info.label}</h3>
      <p>${info.address}</p>
      <p>면적: ${info.area}㎡</p>
      <p>층: ${info.floor}층</p>
      <p class="price">${info.amount}만원</p>
    `;
    container.appendChild(card);
  });
};