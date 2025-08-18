console.log('pwdupdate XXOK');

// [2] 새로운 패스워드 수정 newpwd
const onPwdUpdate = async () => {
    const mpwd = document.querySelector('.oldpwd').value;
    const newpwd = document.querySelector('.newpwd').value;

    const obj = {
        oldpwd,
        newpwd
    }

    try {
        // (1) 비번 확인
        const checkResponse = await fetch(`/member/check?type=mpwd&data=${mpwd}`)
        const checkData = await checkResponse.json();

        if (checkData == false) {
            alert('기존 패스워드가 일치하지 않습니다.')
            return;
        }

        // (2) 비번 수정
        const option = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch(`/member/update/password`, option);
        const data = await response.json();

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