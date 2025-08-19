console.log('pwdupdate XXOK');

// [1] 기존 패스워드 확인 oldpwd
const checkpwd = async () => {
    const oldpwd = document.querySelector('.oldpwd').value;
    try {
        // (1) 비번 확인
        const response = await fetch(`/member/check?type=mpwd&data=${oldpwd}`)
        const data = await response.json();

        if (data == false) {
            alert('기존 패스워드가 일치하지 않습니다.')
            return;
        }

    } catch (error) {
        console.log(error);
    }

}

// [2] 새로운 패스워드 수정 newpwd
const onPwdUpdate = async () => {
    await checkpwd();
    const newpwd = document.querySelector('.newpwd').value;
    const oldpwd = document.querySelector('.oldpwd').value;
    const obj = {
        newpwd,
        oldpwd
    }
    try {

        // (2) 비번 수정
        const option = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch(`/member/update/password`, option);
        const data = await response.json();

        // 유효성검사
        if (!oldpwd || !newpwd) {
            alert('기존 비밀번호와 새로운 비밀번호를 모두 입력해주세요.');
            return;
        }

        if (oldpwd == newpwd) {
            alert('새로운 비밀번호는 기존 비밀번호와 달라야 합니다.');
            return;
        }

        if (data == true) {
            alert('비밀번호가 변경되었습니다.')
            location.href = '/index.jsp';
        } else {
            alert('비밀번호 변경에 실패했습니다.')
        }

    } catch (error) {
        console.log(error);
    }
}