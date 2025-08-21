console.log('productList XXOK');
let productList = [];



// [1] 모든 제품 정보 가져오기

const getList = async () => {
    // fetch
    const response = await fetch('/product/list');
    const data = await response.json();
    console.log(data); // 확인
    // 전역변수로 제품 정보 저장하기
    productList = data;
} // func end


// [2] 카카오지도
const getMap = async () => {
    // 현재 사용자의 위치 좌표 가져오기
    const position = await myPosition(); // /js/kakao/position.js 파일 함수 호출
    var map = new kakao.maps.Map(document.getElementById('map'), { // 지도를 표시할 div
        center: new kakao.maps.LatLng(position.coords.latitude, position.coords.longitude), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    });

    // 2. 마커 클러스터러를 생성합니다
    var clusterer = new kakao.maps.MarkerClusterer({
        map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
        averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
        minLevel: 10, // 클러스터 할 최소 지도 레벨
        disableClickZoom: true // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
    });

    // 3. 전역변수에 있는 제품리스트 마커로 생성
    let markers = productList.map((product) => {
        // return new kakao.maps.Marker({
        //     position : new kakao.maps.LatLng(position.plat , position.plng)
        // })
        // 3-1 마커 생성
        let marker = new kakao.maps.Marker({
            position: new kakao.maps.LatLng(product.plat, product.plng)
        })
        // 3-2 마커 클릭 이벤트
        kakao.maps.event.addListener(marker, 'click', () => {
            alert('클릭한 제품명은 : ' + product.pname);
            // * 업로드된 이미지 출력
            // 1. 어디에
            const productDiv = document.querySelector('#product');
            // 2. 무엇을 , JSP(webapp 이하 경로) , css/js/img(static 이하 경로) , JAVA/Spring(controller 매핑 경로)
            let html = ``;

            // 이미지가 없는 경우
            if (product.images.length == 0) {
                html += `<img src="/upload/default.png" />`;
            } else { // 이미지 다수일 때
                for (let i = 0; i < product.images.length; i++) {
                    let img = product.images[i];
                    html += `<img src="/upload/${img}" />`;
                }
            }
            // html += `<img src="/upload/${product.images}"/>`;  // http://localhost:8080 생략 가능
            // 3. 출력
            productDiv.innerHTML = html;

        })
        // 3-3 마커 반환을 .map에서 실행
        return marker;
    })
    clusterer.addMarkers(markers); // 클러스트러에 마커들을 추가합니다


    // $.get("/download/web/data/chicken.json", function(data) {
    // // 데이터에서 좌표 값을 가지고 마커를 표시합니다
    // // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
    // var markers = $(data.positions).map(function(i, position) {
    //     return new kakao.maps.Marker({
    //         position : new kakao.maps.LatLng(position.lat, position.lng)
    //     });
    // });



    // 4. 마커 클러스터러에 클릭이벤트를 등록합니다
    kakao.maps.event.addListener(clusterer, 'clusterclick', function (cluster) {

        // 현재 지도 레벨에서 1레벨 확대한 레벨
        var level = map.getLevel() - 1;

        // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
        map.setLevel(level, { anchor: cluster.getCenter() });
    });

} // func end

// [*] 함수 동기화
const _init = async () => {
    await getList(); // 카카오 맵이 실행되기 전에 제품 정보 가져오기 먼저 실행
    await getMap();
}
_init();

/*
    for문으로 마커 찍기
    // 3. 전역변수에 있는 제품리스트 마커로 생성
        let markerList = []; // 여러 마커를 저장하는 배열
        // 3-1 map 반복문을 이용한 전역변수의 제품리스트 반복하기
        for (let i = 0; i < productList.length; i++){
            // 3-2 제품리스트에서 i번째 제품 객체 1개 반환
            const product = productList[i]    
            // 3-3 카카오 API의 마커 객체 생성
            let marker = new kakao.maps.Marker({
                position : new kakao.maps.LatLng(product.plat , product.plng)
            });
            // 3-4 마커들을 저장하는 마커리스트에 .push
            markerList.push(marker);
        }
        // 3-5 반복문 끝나고 마커들을 클러스터에 넣기
        // 클러스터러에 마커들을 추가합니다
        clusterer.addMarkers(markerList);
*/