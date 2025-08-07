// (*) 연동 확인
console.log("save XXOK");

// [1] 등록 fetch
const save = async () => {
    // 1. 입력받은 값 가져오기
    const snameInput = document.querySelector('.sname');
    const skorInput = document.querySelector('.skor');
    const smathInput = document.querySelector('.smath');
    const sname = snameInput.value;
    const skor = skorInput.value;
    const smath = smathInput.value;
    // 2. 입력받은 값 객체화
    // 속성명과 속성값변수명 같으면 생략 가능
    const object = {
        sname : sname,
        skor : skor,
        smath : smath
        // cosst data {sname , skor , smath} 이렇게 써도 됨
    }
    // (*) 유효성검사 (생략)
    // 3. fetch option , 템플릿임
    const option = {
        method : "POST" ,
        headers : { "Content-Type" : "application/json" } ,
        body : JSON.stringify(object)
    }
    // 4. fetch 실행(exe)
    const response = await fetch("/student/save" , option);
    const data = await response.json();
    // 5. 결과가 true면
    if ( data == true) {
        alert('등록 성공');
        location.href="/student/find.jsp"; // JS 페이지 전환 
    } else {
        alert('등록 실패');
    }

} // func end
