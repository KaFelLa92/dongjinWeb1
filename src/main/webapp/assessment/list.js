console.log('list XXOK');

// [1] 회원목록조회
// 가입일자는 YYYY-MM-DD 형식
// 고객등급은 A = VIP , B = 일반 , C = 직원
// 회원번호 클릭하면 회원 정보 수정 페이지로 이동

const memberList = async () => {
    // GET 방식이라 Option 생략, 리스폰스 데이터 ㄱㄱ
    const response = await fetch('/assessment/list');
    const data = await response.json();
    // 어디에 출력할 것인가
    const memberListTbody = document.querySelector('#memberListTbody');
    // 출력식
    let html = ``;
    for (let i = 0; i < data.length; i++) {
        const list = data[i];
        let gradeTran = '';
        if (list.grade == 'A') {
            gradeTran = 'VIP'
        } else if (list.grade == 'B'){
            gradeTran = '일반'
        } else if (list.grade == 'C'){
            gradeTran = '직원'
        } else {
            gradeTran = list.grade;
        }
        
        html += `<tr>
                    <td> 
                        <a href="/assessment/update.jsp?custno=${list.custno}">
                            ${list.custno} 
                        </a>
                    </td>
                    <td> ${list.custname} </td>
                    <td> ${list.phone} </td>
                    <td> ${list.address} </td>
                    <td> ${list.joindate} </td>
                    <td> ${gradeTran} </td>
                    <td> ${list.city} </td>
                </tr>`
    }
    //출력
    memberListTbody.innerHTML = html;
}
memberList();
