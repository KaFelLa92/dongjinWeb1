// 연동 확인
console.log('view XXOK');

// [1] 개별조회
const boardFind = async () => {
    console.log('boardFind XXOK');
    // 1. URL 경로 쿼리스트링 가져오기 클릭한 bno 가져오기
    // (1) 경로상 웹주소 가져오기
    const url = new URLSearchParams(location.search);
    // (2) 웹주소에서 쿼리스트링 값 가져오기
    const bno = url.get('bno');
    // 2. 클릭한 bno를 fetch 통신 , 백틱 이용한 쿼리스트링
    const response = await fetch(`/board/find?bno=${bno}`);
    const data = await response.json();
    // 3. 어디에
    const bcontentBox = document.querySelector('.bcontentBox');
    const bwriterBox = document.querySelector('.bwriterBox');
    // 4. 무엇을
    const bcontent = data.bcontent;
    const bwriter = data.bwriter;
    // 5. 출력식
    bcontentBox.innerHTML = bcontent;
    bwriterBox.innerHTML = bwriter;
}
boardFind();

// [2] 삭제
const boardDelete = async () => {
    console.log('boardDelete XXOK');
    // 1. 현재 삭제할 게시물 번호 주소에서 가져오기
    const bno = new URLSearchParams(location.search).get('bno');
    // 2. 유효성 검사
    let check = confirm('정말 삭제할까요?')
    if (check == true) {
        // 3. check 
        const option = { method: "DELETE" }
        const response = await fetch(`/board?bno=${bno}` , option)
        const data = await response.json();
        if (data == true) {
            alert('삭제 성공');
            location.href = "/board/list.jsp"
        } else {
            alert('삭제 실패');
        }
    }
}

// [3] 수정페이지로 이동