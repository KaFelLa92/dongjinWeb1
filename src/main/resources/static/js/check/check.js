// [1] 아이디 중복검사
const idcheck = async () => {
    // 1. 값 불러오기
    const mid = document.querySelector('.idInput').value;
    const idcheck = document.querySelector('.idcheck');
    // 2. 유효성 검사 (길이 검사)
    if (mid.length < 6) {
        idcheck.innerHTML = "아이디는 6글자 이상으로 가능합니다."
        signPass[0] = false;
        return; // 함수 강제 종료
    }
    // 2. 유효성 검사 (중복 검사)
    const option = { method: "GET" };
    const response = await fetch(`/member/check?type=mid&data=${mid}`, option);
    const data = await response.json();

    if (data == true) {
        idcheck.innerHTML = "사용중인 아이디입니다."
        signPass[0] = true;
    } else {
        idcheck.innerHTML = "사용가능한 아이디입니다."
        signPass[0] = true;
    }
}

// [2] 연락처 중복검사
const phonecheck = async () => {
    const mphone = document.querySelector('.phoneInput').value;
    const phonecheck = document.querySelector('.phonecheck');
    // 2. 유효성 검사
    if (mphone.length != 13) {
        phonecheck.innerHTML = `-(하이픈) 포함한 13글자 연락처 입력해주세요.`
        signPass[1] = false;
        return;
    }
    // 2. 유효성검사 (중복검사)
    try {
        const option = { method: "GET" };
        const response = await fetch(`/member/check?type=mphone&data=${mphone}`, option);
        const data = await response.json();

        if (data == true) {
            phonecheck.innerHTML = "현재 사용중인 연락처입니다."
            signPass[1] = false;
        } else {
            phonecheck.innerHTML = "사용 가능한 연락처입니다."
            signPass[1] = true;
        }
    } catch (error) {
        console.log(error);
    }


}