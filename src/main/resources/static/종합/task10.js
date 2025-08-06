// 1. 연동 확인
console.log("task10 xxok");

// 2. 함수 연동 확인
// 1) 대기등록 함수
const waitAdd = () => {
    console.log("waitAdd xxok");
    // 3. fetch 구현
    // (1) 보낼 데이터 객체 샘플
    let data = { "wnumber" : "010-1599-1599" , "wcount" : 3 }
    // (2) fetch 옵션 3가지 : method , headers , body
    let option = {
        // method
        method : "POST" ,
        // headers
        headers : { "Content-Type" : "application/json"},
        // body
        body : JSON.stringify(data)
    }
    // (3) fetch 실행 (url , option)
    // .then (response => response.json())
    // .then (data => { console.log(data) })
    // .catch (error => { console.log(error) })
    fetch("/waiting" , option)
    .then( response => response.json())
    .then( data => {console.log(data)})
    .catch( error => {console.log(error)})
} // func end

// 2) 대기조회 함수
const waitPrint = () => {
    console.log("waitPrint xxok");
    // 3. fetch 구현
    // (1) 보낼 데이터 객체 샘플 - 없음
    // (2) fetch 옵션 : method(GET은 생략 가능)
    let option = {method : "GET"}
    // (3) fetch 실행 (개별조회함수일 경우 백틱 사용)
    fetch("/waiting" , option)
    .then( response => response.json())
    .then( data => {console.log(data)})
    .catch( error => {console.log(error)})
} // func end

// 3) 대기삭제 함수
const waitDelete = () => {
    console.log("waitDelete xxok");
    // 3. fetch 구현
    // (1) 보낼 데이터 객체 샘플    존재하는 wno
    let wno = 3;
    // (2) fetch 옵션 : method
    let option = { method : "DELETE"}
    // (3) fetch 실행 (쿼리스트링이므로 백틱 사용)
    fetch(`/waiting?wno=${wno}` , option)
    .then(response => response.json())
    .then(data => {console.log(data);})
    .catch( error => {console.log(error)})
} // func end

// 4) 대기수정 함수
const waitUpdate = () => {
    console.log("waitUpdate xxok");
    // 3. fetch 구현
    // (1) 보낼 데이터 객체 샘플
    let data = {"wno" : 5 , "wcount" : 127}
    // (2) fetch 옵션 3가지 : method , headers , body
    let option = {
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(data)
    } // option end
    // (3) fetch 실행
    fetch("/waiting" , option)
    .then(response => response.json())
    .then(data => {console.log(data)})
    .catch( error => {console.log(error)})
} // func end

