console.log('exam7 XXOK');

/*

[ 문제 ] 아래 링크에 요구사항을 확인하여 프로그램을 완성하시오.
[요구사항1] 공공데이터 "인천광역시 연수구_관내 학교 현황" API 활용하여 카카오 지도에 마커에 출력하시오.
https://www.data.go.kr/data/15039731/fileData.do#/API%20%EB%AA%A9%EB%A1%9D/getuddi%3A1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0
[ 참고 URL ]
const serviceKey = "nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D"
const URL = "https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey="

1-1. 학교 마커 표시
    - 공공데이터 "인천광역시 연수구_관내 학교 현황" API에서 데이터를 가져온다.
    - 가져온 데이터의 위도, 경도 정보를 활용하여 학교 위치에 마커를 표시한다.
    - 마커는 MarkerClusterer를 이용해 클러스터링한다.

1-2. 마커 클릭 이벤트
    - 사용자가 특정 마커를 클릭하면, 사이드바(div#sidebar)에 해당 학교의 상세 정보를 출력한다.
    출력할 정보: 학교명,전화번호,주소

1-3. 전체 보기 기능
    버튼 클릭 시, API에서 가져온 모든 학교 정보를 사이드바에 리스트 형식으로 출력한다.

[ 제출방법 ] 코드가 작성된 파일이 위치한 깃허브 상세 주소를 제출하시오.

*/
// [1] 학교 마커 표시 (1-3번 기능 위한 함수)
// 1-1. 공공데이터 가져오기
const dataAPI = async () => {
    // 1. URL, 서비스키
    const serviceKey = "saMxAvRlTANKlalraIpRBSAUwbJcdwlJZ5xtXAqK54qUHBCw5bkM67hqK77woqRRwMpPrXN%2B2TfslA46Vt9ErA%3D%3D";
    const URL = "https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey=";
    // 2. fetch
    const response = await fetch(URL + serviceKey);
    const data = await response.json();
    console.log(data);
    // 3. 사이드바 정보 출력
    const sidebar = document.querySelector('#sidebar')
    let html = ``;
    data.data.forEach((value) => {
        html += `<div id="school">
                    <div> ${value.학교명} </div>
                    <div> ${value.교무실} </div>
                    <div> 행정실 : ${value.행정실} </div>
                    <div> ${value.주소} </div>
                </div>`
    });
    sidebar.innerHTML = html;

}
dataAPI(); // 초기화시 1회 출력

// 1-2 데이터 이용해 지도에 마커 표시하기
const kakaoMarker = async () => {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
        mapOption = {
            center: new kakao.maps.LatLng(37.41038125, 126.6782658), // 연수구 지도의 중심좌표
            level: 5 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 클러스터러 생성
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
    });

    // 1. URL, 서비스키
    const serviceKey = "saMxAvRlTANKlalraIpRBSAUwbJcdwlJZ5xtXAqK54qUHBCw5bkM67hqK77woqRRwMpPrXN%2B2TfslA46Vt9ErA%3D%3D";
    const URL = "https://api.odcloud.kr/api/15039731/v1/uddi:1fcb72a0-ba75-4c97-a045-9ef7e3ef43c0?page=1&perPage=72&serviceKey=";
    // 2. fetch
    const response = await fetch(URL + serviceKey);
    const data = await response.json();
    console.log(data);

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    // 마커 생성
    let markers = data.data.map((value) => {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(value.위도, value.경도)

        });
        // [2] 마커 클릭 이벤트
        kakao.maps.event.addListener(marker, 'click', () => {
            const sidebar = document.querySelector('#sidebar')
            // [3] 전체보기 사이드바에 모든 학교 출력
            let html = `<button type="button" class="viewBtn" onclick="dataAPI()"> 전체보기 </button>
                        <div id="school"
                            <div> 교명 : ${value.학교명} </div>
                            <div> 교무실 : ${value.교무실} </div>
                            <div> 행정실 : ${value.행정실} </div>
                            <div> 주소 : ${value.주소} </div>
                        </div>`
            sidebar.innerHTML = html;

        })
        // 마커 리턴
        return marker;
    })

    // 클러스터러에 마커  추가
    clusterer.addMarkers(markers);

}
kakaoMarker();
