console.log('data XXOK');

// [1] 인천 부평구 주유소 현황
const dataAPI1 = async () => {

    // (1) 요청 URL , perPage=10 -> 38로 변경
    const serviceKey = "saMxAvRlTANKlalraIpRBSAUwbJcdwlJZ5xtXAqK54qUHBCw5bkM67hqK77woqRRwMpPrXN%2B2TfslA46Vt9ErA%3D%3D";
    const URL = "https://api.odcloud.kr/api/15102672/v1/uddi:d26dabc4-e094-463d-a4b1-cab3af66bb6d?page=1&perPage=38&serviceKey=";

    // (2) fetch 활용한 공공데이터 API 요청
    const option = { method: "GET" } // GET 방식은 생략 가능
    const response = await fetch(URL + serviceKey, option)
    const data = await response.json();
    console.log(data); // 요청 결과값 콘솔 찍어서 확인 후 분석하여 사용
    // 커런트 카운트가 38이기에, URL에서 perPage를 38로 바꾸면 전부 출력 가능
    // data : 실질적인 데이터가 들어오는 속성명
    console.log(data.data); // 배열화된 객체 데이터만 나열됨. 이게 *실질적으로 필요*한 데이터
    // (3) JSP(html) 표에 출력하기
    // 1. 어디에
    const dataTbody = document.querySelector('#dataTbody');
    // 2. 무엇을
    let html = '';
    // array.forEach((value) => { }); : 리스트 요소를 마지막 인덱스까지 하나씩 변수(value)에 반복 대입한다.
    // 하단의 일반 for문과 같음
    // for (let i = 0; i < data.data.length; i++) {
    data.data.forEach((value) => {
        html += `<tr>
                    <td> ${value.연번} </td>
                    <td> ${value.상호} </td>                            
                    <td> ${value.업종} </td>
                    <td> ${value.전화번호} </td>
                    <td> ${value['주소']} </td>
                <tr>`
    });
    // }
    // 이스케이프 문자 같은 특수문자가 value값일 경우, [''] 형식으로 처리하기 예] value['주소']
    // 3. 출력
    dataTbody.innerHTML = html;

} // func end
dataAPI1(); // 초기화 시 1회 실행

/*

    1. https://www.data.go.kr/
    2. 간편 로그인
    3. 부평구 주유소 검색   https://www.data.go.kr/data/15102672/fileData.do#tab-layer-openapi
    4. JSON 활용신청
    5. 활용 목적 : 연구(논문) , 웹개발 테스트
    6. 마이페이지 -> 데이터 활용 -> Open API -> 활용신청 현황 -> 승인된 API 확인후 클릭
    7. 개발계정 상세보기 -> Open API 명세 확인 가이드 확인
    8. 인증키 설정에서 첫 칸에는 일반인증키(Encording), 두 번째 칸에는 일반인증키(Decording) 복붙
    9. Open API 실행 준비 클릭 -> 오픈 API 호출
    * 개인 API인증키는 마이페이지에서 확인

*/


// [2] 사업자등록정보 상태조회 서비스

const dataAPI2 = async () => {
    // 1. 데이터 준비
    const b_no = document.querySelector('.b_no').value; // 입력받은 사업자번호
    const obj = {// var는 중복 가능한 변수타입. let, const로 대체
        "b_no" : [b_no] // 하이픈(-)없는 사업자번호 준비
    }
    // 2. fetch
    const option = {
        method : "POST" ,
        headers : {"Content-Type" : "application/json"} ,
        body : JSON.stringify(obj)
    }
    const serviceKey = 'saMxAvRlTANKlalraIpRBSAUwbJcdwlJZ5xtXAqK54qUHBCw5bkM67hqK77woqRRwMpPrXN%2B2TfslA46Vt9ErA%3D%3D';
    const URL = 'https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=';
    const response = await fetch( URL + serviceKey , option );
    const data = await response.json();
    console.log( data );
    alert(data.data[0]["tax_type"])

}

/*
    var data = {
        "b_no": ["xxxxxxx"] // 사업자번호 "xxxxxxx" 로 조회 시,
    }; 
    
    $.ajax({
    url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=xxxxxx",  // serviceKey 값을 xxxxxx에 입력
    type: "POST",
    data: JSON.stringify(data), // json 을 string으로 변환하여 전송
    dataType: "JSON",
    contentType: "application/json",
    accept: "application/json",
    success: function(result) {
        console.log(result);
    },
    error: function(result) {
        console.log(result.responseText); //responseText의 에러메세지 확인
    }
    });
*/

/*
    1. 국세청_사업자등록정보 진위확인 및 상태조회 서비스
    2. 사업자등록 상태조회 API    
    3. 상태조회 샘플코드 (jquery ajax method 사용)


*/