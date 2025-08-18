console.log('kakaomap XXOK');

/*

    [ APP KEY 발급 ]
    1. https://apis.map.kakao.com/
    2. 우측 상단 APP KEY 발급
    3. 상단 메뉴 [앱] 탭 -> [앱 생성]
    4. 대시보드 접속
    5. 사이드바 [앱] -> [일반] -> 스크롤 내려서 앱 키 확인 가능
        * JS 카카오맵 사용 시, JavaScript 키 : 8862611df14b8d0f1a6016b7e9a5ae24
    6. '플랫폼' Web 주소 등록 -> [Web 플랫폼 등록]
        http://localhost:8080
        http://192.168.40.136:8080
        * 배포 단계에서는 실제 존재하는 도메인 또는 클라우드 IP 등록
    7. 사이드바 메뉴 -> [카카오맵]
        사용설정 활성화하기
        * 계정 당 카카오맵 1개만 활성화 가능

    [ KAKAO 지도 ]
    1. [WEB] 클릭 -> [시작하기] 내용대로 진행

*/

// [1] 간단한 주소 출력
const kakaoMap1 = async () => {
    // (1) 지도 담을 영역의 DOM 레퍼런스
    var container = document.querySelector('#map'); //지도를 담을 영역의 DOM 레퍼런스
    // (2) 지도 생성에 필요한 기본 옵션
    var options = { //지도를 생성할 때 필요한 기본 옵션
        center: new kakao.maps.LatLng(37.489572382, 126.723325411), //지도의 중심좌표.
        level: 5 //지도의 레벨(확대, 축소 정도) 0이 확대 14가 축소
    };

    var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
} // func end
// kakaoMap1(); 지도 하나씩만 뜨니까 주석 처리

// [2] 클릭한 위치에 마커 표기하기
// https://apis.map.kakao.com/web/sample/addMapClickEventWithMarker/

const kakaoMap2 = async () => {
    // 1. 지도 표시할 마크다운
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.489572382, 126.723325411), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    // 2. 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    // 3. 지도를 클릭한 위치에 표출할 마커 // 지도를 클릭한 위치에 표출할 마커입니다
    var marker = new kakao.maps.Marker({
        // 지도 중심좌표에 마커를 생성합니다 
        position: map.getCenter()
    });
    // 4. 지도에 마커를 표시합니다
    marker.setMap(map);

    // 5. 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function (mouseEvent) {
        // 클릭한 위도, 경도 정보를 가져옵니다 
        var latlng = mouseEvent.latLng;
        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);
        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';
        console.log(message);
    });
}
// kakaoMap2(); 

// [3] 마커에 클릭 이벤트 등록하기

const kakaoMap3 = async () => {
    // 1. 지도를 표시할 Div
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        // 2. 지도의 옵션
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    // 3. 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    // 4. 마커를 표시할 위치입니다 
    var position = new kakao.maps.LatLng(33.450701, 126.570667);
    // 5. 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        position: position,
        clickable: true // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    });

    // 아래 코드는 위의 마커를 생성하는 코드에서 clickable: true 와 같이
    // 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정합니다
    // marker.setClickable(true);

    // 6. 마커를 지도에 표시합니다.
    marker.setMap(map);

    // // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
    // var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    //     iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

    // // 인포윈도우를 생성합니다
    // var infowindow = new kakao.maps.InfoWindow({
    //     content: iwContent,
    //     removable: iwRemoveable
    // });

    // 7. 마커에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(marker, 'click', function () {
        // 마커 클릭시 실행되는 코드들
        alert('해당 마커 클릭 했습니다.')
    });

    // 클릭이벤트 후 부트스트랩 라이브러리의 사이드바 출력시키면 됨
}
// kakaoMap3();

// [4] 여러개 마커 표시하기
const kakaoMap4 = async () => {
    // 1. 지도 표시 div , 옵션
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
        mapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };
    // 2. 지도 생성
    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

    // 3. 마커를 표시할 위치와 title 객체 배열입니다 
    var positions = [
        {
            title: '카카오',
            latlng: new kakao.maps.LatLng(33.450705, 126.570677)
        },
        {
            title: '생태연못',
            latlng: new kakao.maps.LatLng(33.450936, 126.569477)
        },
        {
            title: '텃밭',
            latlng: new kakao.maps.LatLng(33.450879, 126.569940)
        },
        {
            title: '근린공원',
            latlng: new kakao.maps.LatLng(33.451393, 126.570738)
        }
    ];

    // 4. 마커 이미지의 이미지 주소입니다
    var imageSrc = "http://localhost:8080/img/logo.jpg"; // 로컬호스트 로고로 변경함
    // 5. 반복문 이용한 여러개 마커 생성
    for (var i = 0; i < positions.length; i++) {

        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(30, 26);

        // 마커 이미지를 생성합니다    
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
            map: map, // 마커를 표시할 지도
            position: positions[i].latlng, // 마커를 표시할 위치
            title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            image: markerImage // 마커 이미지 
        });
    }

}
kakaoMap4();