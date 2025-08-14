console.log('signup XXOK');

// [1] 회원가입
const signup = async () => {
    // 유효성검사 체크리스트에 false 존재하면 회원가입 진행 불가
    // JS배열내 요소찾기 함수 : 1. .indexOf() 2. .includes()
    if (signPass.includes(false)) {
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
    } catch (error) {
        console.log(error);
    }
}

// 유효성검사 체크리스트
const signPass = [false, false]; // 초기값 실패. 0인덱스 아이디체크, 1인덱스 연락처체크
