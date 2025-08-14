console.log('login XXOK');

const login = async () => {
    // 마크업 가져오기
    const idInput = document.querySelector('.idInput');
    const pwdInput = document.querySelector('.pwdInput');
    // 입력받은 값 가져오기
    const mid = idInput.value;
    const mpwd = pwdInput.value
    // 객체화
    const obj = {
        mid,
        mpwd
    }
    const option = {
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(obj)
    }
    // fetch exe
    const response = await fetch ("/member/login" , option)
    const data = await response.json();
    // result
    if (data > 0) { // 0보다 크면 로그인 성공. 회원번호 반환. 설계를 int 반환으로 했기에, data는 0보다 클 때로 가야함. 불리언이면 data == true
        alert('로그인 되었습니다')
        location.href = "/index.jsp";
    } else {
        alert('로그인 실패. 아이디와 비밀번호를 확인하세요.')
    }

} // func end