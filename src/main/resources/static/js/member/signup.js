console.log('signup XXOK');

// [1] 회원가입
const signup = async () => {
    // 유효성검사 체크리스트에 false 존재하면 회원가입 진행 불가
    if (signPass[0] == false){
        alert('올바른 정보를 입력후 가능합니다.')
        return;
    }
    // 마크업 DOM 객체 가져오기
    const idInput = document.querySelector('.idInput');
    const pwdInput = document.querySelector('.pwdInput');
    const nameInput = document.querySelector('.nameInput');
    const phoneInput = document.querySelector('.phoneInput');
    // 마크업에 밸류 넣기
    const mid = idInput.value;
    const mpwd = pwdInput.value;
    const mname = nameInput.value;
    const mphone = phoneInput.value;
    // 객체화
    const obj = {
        mid,
        mpwd,
        mname,
        mphone
    }

    // 여기에 유효성 검사 넣기
    if (!mid) {
        alert('아이디를 입력하세요.')
        return;
    } else if (!mpwd) {
        alert('비밀번호를 입력하세요.')
        return;
    } else if (!mname) {
        alert('성함을 입력하세요.')
        return;
    } else if (!mphone) {
        alert('연락처를 입력하세요.')
        return;
    }

    // fetch
    try {
        const option = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch('/member/signup', option)
        const data = await response.json();
        if (data > 0) {
            alert('회원가입 완료')
            location.href = "/member/login.jsp";
        } else {
            alert('회원가입 실패')
        }
    } catch(error) {
        console.log(error);
    }
}

// 유효성검사 체크리스트
const signPass = [ false ]; // 초기값 실패. 0인덱스 아이디체크, 1인덱스 연락처체크

// [2] 아이디 중복검사
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
    const option = {method : "GET"};
    const response = await fetch (`/member/check?type=mid&data=${mid}` , option);
    const data = await response.json();

    if(data == true ){
        idcheck.innerHTML = "사용중인 아이디입니다."
    } else{
        idcheck.innerHTML = "사용가능한 아이디입니다."
    }
}

// [3] 연락처 중복검사
const phonecheck = async () => {
    
}


