
# Kakao Map API 사용 가이드라인

## 문제 원인

카카오 맵이 화면에 정상적으로 출력되지 않는 이유는 `exam7.js` 파일의 `kakaoMarker` 함수에서 생성된 마커를 지도에 표시하는 코드가 누락되었기 때문입니다. 또한, 마커 클러스터러를 생성하고 마커들을 추가하는 코드도 빠져있습니다.

## 해결 방법

### 1. Kakao Map 라이브러리 추가

JSP 파일에 Kakao Map 클러스터러 라이브러리를 추가해야 합니다.

```html
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY&libraries=services,clusterer,drawing"></script>
```

### 2. `exam7.js` 파일 수정

`kakaoMarker` 함수에 다음 코드를 추가하여 마커 클러스터러를 생성하고, 생성된 마커를 클러스터러에 추가한 후 지도에 표시해야 합니다.

```javascript
// 마커 클러스터러를 생성합니다
var clusterer = new kakao.maps.MarkerClusterer({
    map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
    averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
    minLevel: 10 // 클러스터 할 최소 지도 레벨
});

// 클러스터러에 마커들을 추가합니다
clusterer.addMarkers(markers);
```

### 수정된 `kakaoMarker` 함수 전체 코드

```javascript
const kakaoMarker = async () => {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
        mapOption = {
            center: new kakao.maps.LatLng(37.41038125, 126.6782658), // 연수구 지도의 중심좌표
            level: 10 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

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
            // [3] 사이드바에 모든 학교 출력
            let html = `<button type="button" onclick="dataAPI()"> 전체보기 </button>
                        <div id="school">
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

    // 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10 // 클러스터 할 최소 지도 레벨
    });

    // 클러스터러에 마커들을 추가합니다
    clusterer.addMarkers(markers);
}

kakaoMarker();
```

## 추가 정보

- [Kakao Maps API 공식 문서 - 마커 클러스터러](https://apis.map.kakao.com/web/sample/clusterer/)
- [공공데이터포털](https://www.data.go.kr/)

