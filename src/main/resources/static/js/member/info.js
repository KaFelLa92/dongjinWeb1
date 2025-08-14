console.log('info XXOK');

// [1] 내 정보 조회

const onInfo = async () => {
    try {
        // get 맵핑
        const response = await fetch("/member/info")
        const data = await response.json();
        // fetch결과 로그인이면 정보 가져오기
        const mnoInput = document.querySelector('.mno');
        const midInput = document.querySelector('.mid');
        const mnameInput = document.querySelector('.mname');
        const mphoneInput = document.querySelector('.mphone');
        const mdateInput = document.querySelector('.mdate');
        // 출력
        mnoInput.innerHTML = data.mno;
        midInput.innerHTML = data.mid;
        mnameInput.innerHTML = data.mname;
        mphoneInput.innerHTML = data.mphone;
        mdateInput.innerHTML = data.mdate;
    } catch (error) {
        console.log(error);
        location.href = "/member/login.jsp"; // 로그인 페이지로
    }
    // 어디에
}
onInfo();

// [2] 회원 탈퇴
// alert() : 확인 알람 , prompt() : 입력상자 알람 , confirm : 확인/취소 알람

const onDelete = async () => {
    // 1. 삭제 확인
    let result = confirm('정말 탈퇴하시겠습니까?');
    if(result == false) {
        return; // 함수 종료
    }
    // 2. 기존 패스워드 확인
    let oldpwd = prompt('현재 비밀번호 입력해주세요.');
    // 3. fetch
    try{
        const option = { method : "DELETE" }
        const response = await fetch (`/member/delete?oldpwd=${oldpwd}` , option)
        const data = await response.json();
        if (data == true){
            alert('탈퇴 성공');
            location.href="/index.jsp";
        } else {
            alert('탈퇴 실패 : 비밀번호가 일치하지 않습니다.')
        }

    } catch (error) {
        console.log(error)
    }
} // func end