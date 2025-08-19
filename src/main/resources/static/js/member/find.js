console.log('find XXOK');

// [1] 아이디찾기
//1. 아이디 찾기 기능
//    - 입력: 이름, 연락처
//    - 처리: 이름+연락처 일치 시 아이디 반환
//    - 불일치 시 "회원정보 없음" 메시지

const findId = async () => {
    // 마크 다운 단위 객체화
    const mname = document.querySelector('.mname').value;
    const mphone = document.querySelector('.mphone').value;
    // 유효성 검사
    if (mname.length == 0) {
        alert('이름을 입력해주세요.')
    } else if (mphone.length != 13) {
        alert('-(하이픈) 포함한 13글자 연락처 입력해주세요.');
    }
    // fetch 단위
    try {
        const response = await fetch(`/member/find?mname=${mname}&mphone=${mphone}`)
        const data = await response.json();
        if (data) {
            alert("당신의 아이디는 " + (data.mid) + "입니다.")
            return;
        } else {
            alert("이름과 연락처를 확인해주세요.")
        }

    } catch (error) {
        console.log(error)
    }

    // 데이터 일치하는지 확인

    // 리턴
}


// [2] 비밀번호 찾기
//2. 비밀번호 찾기 기능
//    - 입력: 아이디, 연락처
//    - 처리: 아이디+연락처 일치 시 새로운 난수(6자릿수) 비밀번호 생성 후 반환
//    - 생성된 비밀번호를 DB에 업데이트(임시 비밀번호로 사용)

const findPwd = async () => {
    // 마크 다운 단위 확인
    const mid = document.querySelector('.mid').value;
    const mphone = document.querySelector('.mphone2').value;

    const obj = {
        mid,
        mphone 
    }

    // 유효성 검사
    if (mid.length == 0) {
        alert('아이디를 입력해주세요.')
    } else if (mphone.length != 13) {
        alert('-(하이픈) 포함한 13글자 연락처 입력해주세요.');
    }

    // fetch
    try {
        const option = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(obj)
        }
        const response = await fetch (`/member/find` , option);
        const data = await response.json();

        if (data) {
            alert ("새로운 비밀번호 : " + data);
            return;
        } else {
            alert("아이디와 연락처를 확인해주세요.")
        }

    } catch (error) {
        console.log(error);
    }


}