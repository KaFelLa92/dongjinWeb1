// (*) 연동 확인
console.log("waitAdd XXOK");

// [1] 등록 fetch
const waitAdd = async () => {
    // 1. 입력받은 값 가져오기
    const wnumberInput = document.querySelector('.wnumber');
    const wcountInput = document.querySelector('.wcount');
    const wnumber = wnumberInput.value;
    const wcount = wcountInput.value;
    // 2. 입력받은 값 객체화
    // 속성명과 속성값, 변수명 같으면 생략 가능
    const object = {
        wnumber,
        wcount
    } 
    // (*) 유효성검사
    if (!wnumber){
        alert('연락처를 입력해주세요.')
        location.href="/waiting/add.jsp";
        return; // 함수 종료
    }
    if (!wcount){
        alert('인원 수를 입력해주세요.')
        location.href="/waiting/add.jsp";
        return; // 함수 종료
    }
    // 3. fetch option
    const option = {
        method : "POST" ,
        headers : { "Content-Type" : "application/json" },
        body : JSON.stringify(object)
    } 
    // 4. fetch 실행
    const response = await fetch("/waiting/add" , option);
    const data = await response.json();
    // 5. 리턴값 반환
    if ( data == true){
        alert('대기 등록 성공')
        location.href="/waiting/print.jsp"; // 조회 페이지로 전환
    } else {
        alert('대기 등록 실패')
    }

} // func end