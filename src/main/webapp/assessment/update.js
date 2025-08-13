console.log('update XXOK');

// [1] 등록에 정해진 값 투사하기
const memberUpdateView = async () => {
    console.log('memberAddView XXOK');
    // 게시물 번호 가져오기
    const custno = new URLSearchParams(location.search).get('custno');
    // fetch
    const response = await fetch(`/assessment/update?custno=${custno}`);
    const data = await response.json();
    // 회원번호, 가입일자 제외하고 수정 전 value 출력
    const custname = document.querySelector('.custname');
    const phone = document.querySelector('.phone');
    const address = document.querySelector('.address');
    const grade = document.querySelector('.grade');
    const city = document.querySelector('.city');
    // value 넣기
    custname.value = data.custname;
    phone.value = data.phone;
    address.value = data.address;
    grade.value = data.grade;
    city.value = data.city;

    // 이미 value 있는 값들
    // DAO에서 현재 회원번호 +1하는 코드 만들어서 넣기
    const nextNo = document.querySelector('.nextNo');
    nextNo.value = data.nextNo;

    // date는 new Date() 로 오늘 날짜 넣으면 됨
    const date = document.querySelector('.date');
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    date.value = `${year}-${month}-${day}`;
    console.log(date.value);
}
memberUpdateView();

// [2] 수정
const memberUpdate = async () => {
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

    // fetch
    const option = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(obj)
    }
    // fecth exe
    const response = await fetch("/assessment/update", option)
    const data = await response.json();
    // result
    if (data == true) {
        alert('회원정보수정이 완료 되었습니다!')
        location.href = "/assessment/list";
    } else {
        alert('회원정보수정이 실패했습니다. 관리자 문의 010-XXXX-XXXX')
    }

} // func end



// [3] 조회로 이동
const memberToList = async () => {
    console.log('memberToList XXOK');
    location.href = "/assessment/list.jsp";
}
