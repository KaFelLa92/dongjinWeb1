console.log('position XXOK');

// [1] 컴퓨터 IP 기반 위치 조회 , 오차 있을 수 있다. (GPS 기반 아님)
const myPosition = async () => {
    const position = await new Promise ( (resolve , reject)  => {
        // new Promise : 비동기 객체
        // resolve : 성공시 reject : 실패시
        // navigator.geolocation.getCurrentPosition(성공객체, 실패객체, {옵션}) : 현재 브라우저가 ip 기반으로 위도 경도 조회
        navigator.geolocation.getCurrentPosition(resolve , reject , { 
            enableHighAccuracy : true , // 가능한 정확한 위치 잡기 (속도 조금 느림, 전기 더 먹음)
            timeout : 5000 , // 밀리초단위 , 5초 안에 못 가져오면 실패(reject) 반환
            maximumAge : 0 // 캐시(임시) 정보는 사용안함(항상 새로고침)
        }); // navi end
    }); // async func end

    console.log(`위치 정보 : ${position}`)
    console.log(`위도 : ${position.coords.latitude}`)
    console.log(`경도 : ${position.coords.longitude}`)
    return position; // 
} // func end