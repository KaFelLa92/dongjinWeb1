// (*) 연동 함수
console.log('write XXOK');

// [1] 등록
const boardWrite = async () => {
    console.log('boardWrite XXOK');
    // 1. 입력값 가져오기
    const bcontentInput = document.querySelector('.bcontent');
    const bwriterInput = document.querySelector('.bwriter');
    const bcontent = bcontentInput.value;
    const bwriter = bwriterInput.value;
    // 2. 객체화
    const object = {
        bcontent,
        bwriter
    } // 일치하면 똑같이 씀
    // 3. fetch option
    const option = {
        method : "POST",
        headers : { "Content-Type" : "application/json"} , 
        body : JSON.stringify(object)
    }
    // 4. fetch 실행
    const response = await fetch("/board" , option);
    const data = await response.json();
    // 5. true false
    if ( data == true ){
        alert('등록 성공');
        location.href="/board/list.jsp"; // 게시판으로 전환
    } else {
        alert('등록 실패');
    }
} // func end