console.log('sale XXOK');

// [1] 회원매출조회

const memberSaleList = async () => {
    console.log('memberSaleList XXOK');
    // fetch 통신
    const response = await fetch('/assessment/sale');
    const data = await response.json();
    // 출력할 곳
    const memberSaleTbody = document.querySelector('#memberSaleTbody');
    // 출력식
    let html = '';
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
                    <td> ${list.custno} </td>
                    <td> ${list.custname} </td>
                    <td> ${gradeTran} </td>
                    <td> ${list.sale} </td>
                </tr>`
    }
    //출력
    memberSaleTbody.innerHTML = html;
}
memberSaleList();