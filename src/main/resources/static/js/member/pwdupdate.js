console.log('pwdupdate XXOK');

// [1] 기존 패스워드 조회 확인

// [1] 새로운 패스워드 수정
const onPwdUpdate = async () => {
    const newpwd = document.querySelector('.newpwd').value;

    try{
        const option = {
            method : "PUT" ,
            headers : {"Content-Type" : "application/json"},
            body : JSON.stringify(newpwd)
        }
        const response = await fetch ('/member/update/password')
        const data = await response.json();
        if (data == true) {
            
        }

    } catch(error) {
        console.log(error);
    }
}