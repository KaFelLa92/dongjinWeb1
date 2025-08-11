// 연동 확인
console.log('update XXOK');

// [1] 개별 조회 (수정하기 전 내용물 확인용)
const boardFind = async () => {
    console.log('boardFind XXOK')
    // 1. 현재 게시물 번호 가져오기
    const url = new URLSearchParams(location.search);
    const bno = url.get('bno');
    // 2. fetch 이용한 bno 게시물 정보 요청
    const response = await fetch(`/board/find?bno=${bno}`);
    const data = await response.json();
    // 3. 현재 게시물 수정 전 내용 출력
    const bcontent = document.querySelector('.bcontent');
    // 4. 출력
    bcontent.innerHTML = data.bcontent;
}
boardFind(); // JS 실행 시 초기화

// [2] 개별 수정
const boardUpdate = async () => {
    console.log('boardUpdate XXOK');
    // 1. 현재 수정할 게시물 번호 가져오기
    const bno = new URLSearchParams(location.search).get('bno');
    // 2. 수정할 입력받은 값 가져오기
    const bcontent = document.querySelector('.bcontent').value;
    // 3. 객체화
    const obj = {
        bno ,
        bcontent 
    }
    // 4. fetch
    const option = {
        method : "PUT" ,
        headers : {"Content-Type" : "application/json"} ,
        body : JSON.stringify(obj)
    }
    // 5. fetch 실행
    const response = await fetch("/board" , option);
    const data = await response.json();
    // 6. fetch 결과
    if( data == true ){
        alert('수정 성공');
        location.href=`/board/view.jsp?bno=${bno}`; 
        // view.jsp와 update.jsp는 bno를 쿼리스트링 매개변수 사용
    } else {
        alert('수정 실패');
    }
} // func end
