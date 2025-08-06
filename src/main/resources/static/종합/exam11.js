// 1. 연동 테스트
console.log("exam11 XXOK");

// 2. 함수 연동 확인
// 일반 함수 선언 : function 함수명(){ }
// 람다식 함수 선언 : const 함수명 = () => { }

// 1) 등록함수
const boardWrite = () => {
    console.log("boardWrite XXOK")
    // 3. 함수 기능 구현 - fetch
    // (1) 보낼 데이터 객체 준비 - 샘플
    let data = { "bcontent": "JS테스트중", "bwriter": "유재석" }
    // (2) fetch 옵션 : 3가지 (method , header , body)
    let option = {
        // (1) method
        method: "POST",
        // (2) headers
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
        // (3) body
    } // option end
    // (3) fetch (url , option)
    // .then( response => response.json() )
    // .then( data => { } )
    // .catch( error => { } ) 
    fetch("/board" , option )
    .then( response => response.json() )
    .then( data => { console.log(data) } )
    .catch( error => { console.log(error) } )
} // func end

// 2) 조회함수
const boardPrint = () => {
    console.log("boardPrint XXOK")
    // (1) 보낼 데이터 - 없음
    // (2) fetch option
    let option = {method : "GET"} // GET방식 생략 가능
    // (3) fetch 실행
    fetch("/board" , option)
    .then( response => response.json() )
    .then( data => { console.log(data)} )
    .catch( error => { console.log(error)} )
} // func end

// 3) 삭제함수
const boardDelete = () => {
    console.log("boardDelete XXOK")
    // (1) 보낼 데이터 샘플 - bno
    let bno = 4; // 존재하는 bno 아무거나
    // (2) fetch option
    let option = {method : "DELETE"} 
    // (3) fetch 실행 백틱 사용(쿼리스트링)
    fetch(`/board?bno=${bno}` , option)
    .then(response => response.json())
    .then(data => { console.log(data)})
    .catch(error => { console.log(error)})
} // func end

// 4) 수정함수
const boardUpdate = () => {
    console.log("boardUpdate XXOK")
    // (1) 보낼 데이터 샘플
    let data = {"bno" : 3 , "bcontent" : "반갑쌉싸리와용"}
    // (2) fetch option , Talend API 참고해서 작성
    let option = {
        method : "PUT" ,
        headers : {"Content-Type" : "application/json"},
        body: JSON.stringify(data)
    } // option end
    // (3) fetch 실행
    fetch("/board" , option)
    .then(re => re.json())
    .then(data => {console.log(data)})
    .catch(error => {console.log(error)})
} // func end