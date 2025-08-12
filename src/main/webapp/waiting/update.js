// 연동 확인
console.log('update XXOK');

// [1] 개별 조회 (기존 연락처와 인원 수 출력용)
const waitView = async () => {
    console.log('waitView XXOK');
    // 게시물 번호 가져오기
    const wno = new URLSearchParams(location.search).get('wno');
    // fetch
    const response = await fetch(`/waiting/view?wno=${wno}`);
    const data = await response.json();
    // 수정 전 내용 출력식
    const wnumber = document.querySelector('.wnumber');
    const wcount = document.querySelector('.wcount');
    // 출력 구현 (input에는 value로)
    wnumber.value = data.wnumber;
    wcount.value = data.wcount;
}
waitView();

// [2] 수정
const waitUpdate = async () => {
    console.log('waitUpdate XXOK');
    // 게시물번호(쿼리스트링) 가져오기
    const wno = new URLSearchParams(location.search).get('wno');
    // 수정할 입력값 가져오기
    const wnumber = document.querySelector('.wnumber').value;
    const wcount = document.querySelector('.wcount').value;
    // 객체화
    const obj = {
        wno,
        wnumber,
        wcount
    }
    // fetch
    const option = {
        method : "PUT",
        headers : {"Content-Type" : "application/json" },
        body : JSON.stringify(obj)
    }
    // fecth exe
    const response = await fetch('/waiting/update' , option);
    const data = await response.json();
    // return
    if (data == true) {
        alert('대기수정 성공');
        location.href=`/waiting/view.jsp?wno=${wno}`;
    } else{
        alert('대기수정 실패')
    }

} // func end