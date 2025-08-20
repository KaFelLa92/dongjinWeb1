console.log('create XXOK');

// 1. 카카오 지도로 위도 경도 받아오기
// https://apis.map.kakao.com/web/sample/addMapClickEvent/
let latlng = null;

const getMap = async () => {
    var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = {
            center: new kakao.maps.LatLng(37.489572382, 126.723325411), // 부평역 좌표
            level: 3 // 지도의 확대 레벨
        };

    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    // 지도를 클릭한 위치에 표출할 마커입니다
    var marker = new kakao.maps.Marker({ position: map.getCenter() });
    // 지도에 마커를 표시합니다
    marker.setMap(map);

    // 지도에 클릭 이벤트를 등록합니다
    // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
    kakao.maps.event.addListener(map, 'click', function (mouseEvent) {

        // 클릭한 위도, 경도 정보를 가져옵니다 -> 전역변수로 전환하기
        latlng = mouseEvent.latLng; // var 키워드를 제거하거 JS 파일 함수 밖에서 변수 선언
        // 마커 위치를 클릭한 위치로 옮깁니다
        marker.setPosition(latlng);
        var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
        message += '경도는 ' + latlng.getLng() + ' 입니다';

        var resultDiv = document.getElementById('result');
        resultDiv.innerHTML = message;

    });

}
getMap();

// 2. 제품등록하기
const onCreate = async () => {
    // 입력받은 값을 하나씩 가져오는 게 아닌, form 전체를 한 번에 가져오는 방법을 씀
    // 주의할점 : form 안에 속성명 name이, JAVA의 DTO 속성명과 일치하게 함.
    // <input name="pname" /> -----> ProductDto { String pname; }
    if (latlng === null) {
        alert('지도에서 거래 위치를 선택해주세요.');
        return; // 함수 종료
    }

    // 1. 전송할 폼 가져오기
    const productForm = document.querySelector('#productForm');
    // 2. 대용량 첨부파일 폼 전환
    const productFormData = new FormData(productForm);
    // * 위도경도는 폼 안에서 입력받은 형식이 아닌, JS에서 표현한 것으로 직접 폼에 넣기 .append
    productFormData.append('plat', latlng.getLat()); // 위도 [1] 함수에서 구한 위도경도 변수내 위도
    productFormData.append('plng', latlng.getLng()); // 경도 [1] 함수에서 구한 위도경도 변수내 경도
    // * fetch 보내기
    const option = {    // headers {"Content-Type" : "multipart/form-data"} 생략 가능. JSON.stringfy() 할 필요 없음
        method: "POST",
        body: productFormData
    }
    const response = await fetch('/product/create', option)
    const data = await response.json();
    // 로그인 후 테스트 가능
    if (data > 0) {
        alert('등록 성공')
    } else {
        alert('등록 실패')
    }

}
