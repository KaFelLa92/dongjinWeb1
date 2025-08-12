// 연동 확인
console.log('list XXOK');

// [1] 전체조회
const waitList = async () => {
    console.log('waitList XXOK');
    // GET 생략 가능
    const response = await fetch('/waiting/list');
    // 타입변환
    const data = await response.json();
    // 어디에 깔 것인가 = 테이블
    const waitTbody = document.querySelector('#waitTbody');
    // 출력식
    let html =``;
    for (let i = 0; i < data.length; i++){
        const wait = data[i];
        html += `<tr>
                    <td> ${wait.wno} </td>
                    <td> 
                        <a href="/waiting/view.jsp?wno=${wait.wno}">
                            ${wait.wnumber} 
                        </a>    
                    </td>
                    <td> 
                        <a href="/waiting/view.jsp?wno=${wait.wno}">
                            ${wait.wcount} 
                        </a>
                    </td>
                    <td> ${wait.wdate} </td>        
                </tr>`
    } // for end
    // 출력
    waitTbody.innerHTML = html;
}
waitList();