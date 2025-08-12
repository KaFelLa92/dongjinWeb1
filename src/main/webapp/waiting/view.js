// 연동 확인
console.log('view XXOK');

// [1] 개별조회
const waitView = async () => {
    console.log('waitView XXOK');
    // URL 경로 쿼리스트링 가져오기
    const wnoSet = new URLSearchParams(location.search).get('wno');
    // fetch
    const response = await fetch(`/waiting/view?wno=${wnoSet}`);
    const data = await response.json();
    // 위치선정
    const wnoBox = document.querySelector('.wnoBox');
    const wnumberBox = document.querySelector('.wnumberBox');
    const wcountBox = document.querySelector('.wcountBox');
    const wdateBox = document.querySelector('.wdateBox');
    // 데이터에서 넣을 값
    const wno = data.wno;
    const wnumber = data.wnumber;
    const wcount = data.wcount;
    const wdate = data.wdate;
    // 출력
    wnoBox.innerHTML = wno;
    wnumberBox.innerHTML = wnumber;
    wcountBox.innerHTML = wcount;
    wdateBox.innerHTML = wdate;
}
waitView();

// [2] 삭제
const waitDelete = async () => {
    console.log('waitDelete XXOK');
    // URL 경로 쿼리스트링 가져오기
    const wno = new URLSearchParams(location.search).get('wno');
    // 삭제 유효성 검사
    let check = confirm('대기 삭제하시겠습니까?')
    if(check == true){
        const option = { method : "DELETE" }
        const response = await fetch(`/waiting/view?wno=${wno}` , option)
        const data = await response.json();
        if (data == true){
            alert('대기삭제 성공')
            location.href = "/waiting/list.jsp";
        } else {
            alert('대기삭제 실패');
        }
    }
}

// [3] 수정페이지 로케이션 변경
const waitToUpdate = () => {
    // URL 경로 쿼리스트링 가져오기
    const wno = new URLSearchParams(location.search).get('wno');
    // 수정페이지로 bno 전달
    location.href = `/waiting/update.jsp?wno=${wno}`;
}
