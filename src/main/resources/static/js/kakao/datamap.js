console.log('datamap XXOK');

// [1] 공공데이터
const dataAPI = async () => {
    // 1. URL, 서비스키
    const serviceKey = 'saMxAvRlTANKlalraIpRBSAUwbJcdwlJZ5xtXAqK54qUHBCw5bkM67hqK77woqRRwMpPrXN%2B2TfslA46Vt9ErA%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=34&serviceKey=';
    // 2. Fetch
    const response = await fetch(URL + serviceKey, { method: "GET" });
    const data = await response.json();
    console.log(data);
    // 3. 사이드바 정보 출력하기(위도/경도 제외*지도에서 쓸 거임*)
    const sidebar = document.querySelector('#sidebar');
    let html = '';
    data.data.forEach((value) => {
        html += `<div id="store"> 
                        <div> ${value.약국명} </div>
                        <div> ${value.전화번호} </div>
                        <div> ${value.소재지도로명주소} </div>
                    </div>`
    });
    sidebar.innerHTML = html;

}
dataAPI();

// [2] 카카오맵

const kakaoMap = async () => {
    // 1. 지도 표시할 div, 지도 중심좌표
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(37.47401607, 126.6432441), // 지도의 중심좌표 
        level: 5 // 지도의 확대 레벨 
    });

    // 2. 마커 클러스터러(마커 여럿이 겹칠 때 도형으로 마커 수 표현)를 생성합니다 
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
        minLevel: 10, // 클러스터 할 최소 지도 레벨 
    });

    // 데이터를 가져오기 위해 jQuery를 사용합니다
    // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다
    // 3. 페치로 공공데이터 활용
    const serviceKey = 'saMxAvRlTANKlalraIpRBSAUwbJcdwlJZ5xtXAqK54qUHBCw5bkM67hqK77woqRRwMpPrXN%2B2TfslA46Vt9ErA%3D%3D';
    const URL = 'https://api.odcloud.kr/api/15051492/v1/uddi:852bbc11-63ed-493e-ab09-caaaf54fd144?page=1&perPage=34&serviceKey=';
    const response = await fetch(URL + serviceKey);
    const data = await response.json();
    console.log(data); // 약국 정보
    // 4. map 함수로 데이터 읽기 (리턴하기 위해 map. 리턴 필요 없으면 forEach)
    // let markers = [];
    // for( let i = 0; i <data.data.length; i++){
    //     let value = data.data[i];
    //     let marker = new kakao.maps.Marker({
    //         position : new kakao.maps.LatLng( value.위도 , value.경도) // 위도 Latitude 경도 Longitude
    //     });
    // }

    let markers = data.data.map((value) => {
        // 5-1. 마커 생성
        let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(value.위도, value.경도) // lat은 위도 lng은 경도
        });
        // 5-2. 마커 클릭 이벤트
        kakao.maps.event.addListener(marker , 'click' , () => {
            // (*) 내가 클릭한 마커 정보를 사이드바에 출력하기
            const sidebar = document.querySelector('#sidebar');
            let html = `<button type="button" class="viewBtn" onclick="dataAPI()"> 전체보기 </button>
                        <div id="store"> 
                            <div> 약국명 : ${ value.약국명 } </div>
                            <div> 전화번호 : ${ value.전화번호 } </div>
                            <div> 주소 : ${ value.소재지도로명주소 } </div>
                        </div>`;
            sidebar.innerHTML = html;
        })
        // 5-3. 마커 리턴
        return marker;
    });

    // 데이터에서 좌표 값을 가지고 마커를 표시합니다
    // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
    // var markers = $(data.positions).map(function (i, position) {
    //     return new kakao.maps.Marker({
    //         position: new kakao.maps.LatLng(position.lat, position.lng)
    //     });
    // });

    // 6. 클러스터러에 여러 마커를 가진 마커스를 추가합니다
    clusterer.addMarkers(markers);

    // 7. 클러스트러에 클릭이벤트 등록
    kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {
        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;
        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, { anchor: cluster.getCenter() });
    });

}
kakaoMap();
