console.log('update XXOK');

// [1] 등록에 정해진 값 투사하기
const memberUpdateView = async () => {
    console.log('memberAddView XXOK');
    // 게시물 번호 가져오기
    const custno = new URLSearchParams(location.search).get('custno');

    // 유효성 검사
    if(!custno){
        alert('회원 번호가 존재하지 않습니다.')
        return;
    }

    // fetch
    const response = await fetch(`/assessment/find?custno=${custno}`);
    const data = await response.json();
    // 회원번호, 가입일자 제외하고 수정 전 value 출력
    const custnoInput = document.querySelector('.custno');
    const custname = document.querySelector('.custname');
    const phone = document.querySelector('.phone');
    const address = document.querySelector('.address');
    const grade = document.querySelector('.grade');
    const city = document.querySelector('.city');
    const joindate = document.querySelector('.joindate');
    // value 넣기
    custnoInput.value = data.custno;
    custname.value = data.custname;
    phone.value = data.phone;
    address.value = data.address;
    grade.value = data.grade;
    joindate.value = data.joindate;
    city.value = data.city;

}
memberUpdateView();

// [2] 수정
const memberUpdate = async () => {
    console.log('memberAdd XXOK');
    // 입력할 값들
    const custno = document.querySelector('.custno').value; // 수정할 대상 확인용. 사용자가 수정은 불가능.
    const custname = document.querySelector('.custname').value;
    const phone = document.querySelector('.phone').value;
    const address = document.querySelector('.address').value;
    const grade = document.querySelector('.grade').value;
    const city = document.querySelector('.city').value;

    // 객체화
    const obj = {
        // custno,
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
