// 연동 확인
console.log('write XXOK');

// [1] 등록
const waitWrite = async () => {
    console.log('waitWrite XXOK');
    // 입력값 호출
    const wnumber = document.querySelector('.wnumber').value;
    const wcount = document.querySelector('.wcount').value;
    // 객체화
    const obj = {
        wnumber, 
        wcount
    }
    // fetch
    const option = {
        method : "POST",
        headers : { "Content-Type" : "application/json" },
        body : JSON.stringify(obj)
    }
    // fetch exe
    const response = await fetch("/waiting/write" , option);
    const data = await response.json();
    // result
    if ( data == true ){
        alert('대기등록 성공')
        location.href="/waiting/list.jsp"; // 리스트로 이동 
    } else {
        alert('대기등록 실패');
    }
} // func end