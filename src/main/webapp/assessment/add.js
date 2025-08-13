// 연동
console.log('add XXOK');

// [1] 등록 
// custno와 joindate는 value 값 먹여주기
// 등록하면 member_tbl_02에 올라감
// 회원번호(자동발생), 가입일자는 회원 등록 인풋에 미리 생성되어 있다.
// 유효성 검사 : 값 입력되어있지 않을 때 등록 클릭하면 '회원성명 입력되지 않았습니다' 출력 (view탭에서 alert()로 )
// 각 항목별로 해야함
// 유효성 검사 통과하면 '회원등록이 완료 되었습니다!' 알러트 뜨고, 테이블에 저장함.
const memberAdd = async () => {
    console.log('memberAdd XXOK');
    // 입력할 값들
    const custname = document.querySelector('.custname').value;
    const phone = document.querySelector('.phone').value;
    const address = document.querySelector('.address').value;
    const grade = document.querySelector('.grade').value;
    const city = document.querySelector('.city').value;

    // 객체화
    const obj = {
        custname,
        phone,
        address,
        grade,
        city
    }

    // 유효성 검사
    if (!custname) {
        alert('회원성명이 입력되지 않았습니다.')
        return;
    } else if (!phone) {
        alert('회원전화가 입력되지 않았습니다.')
        return;
    } else if (!address) {
        alert('회원주소가 입력되지 않았습니다.')
        return;
    } else if (!grade) {
        alert('고객등급이 입력되지 않았습니다.')
        return;
    } else if (!city) {
        alert('도시코드가 입력되지 않았습니다.')
        return;
    }

    // fetch
    const option = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(obj)
    }
    // fecth exe
    const response = await fetch("/assessment/add", option)
    const data = await response.json();
    // result
    if (data == true) {
        alert('회원등록이 완료 되었습니다!')
        location.href = "/assessment/list";
    } else {
        alert('회원등록이 실패했습니다. 관리자 문의 010-XXXX-XXXX')
    }

} // func end

// [2] 등록에 정해진 값 투사하기
const memberAddView = async () => {
    console.log('memberAddView XXOK');
    // fetch
    const response = await fetch("/assessment/add")
    const data = await response.json();
    // 이미 value 있는 값들
    // DAO에서 현재 회원번호 +1하는 코드 만들어서 넣기
    const nextNo = document.querySelector('.nextNo');
    // joindate는 new date() 로 오늘 날짜 넣으면 됨
    const joindate = document.querySelector('.joindate')
    // 출력 구현
    nextNo.value = data.nextNo;
    joindate.value = data.joindate;
}
memberAddView();

// [3] 조회로 이동
const memberToList = async () => {
    console.log('memberToList XXOK');
    location.href="/assessment/list.jsp";
}

