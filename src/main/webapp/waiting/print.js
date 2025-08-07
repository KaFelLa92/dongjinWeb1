// (*) 연동 확인
console.log("waitPrint XXOK");

// [1] 전체 조회 fetch
const waitPrint = async () => {
    // 1. 매개변수 없음(전체 조회라서!)
    // 2. fetch option
    // const option = { method : "GET"}; GET방식 생략 가능
    // 3. fetch 실행
    const response = await fetch("/waiting/print") // option 빼야함
    // 4. 응답객체 json 변환
    const data = await response.json();
    // 5. 출력문 작성 (어디에? waitingTbody, 무엇을? fetch에서 전송받은 리스트, 출력은 .innerHTML)
    const waitingTbody = document.querySelector('#waitingTbody');
    let html = "";
    for (let i = 0; i < data.length; i++){
        const wait = data[i];
        html += `<tr>
                    <td> ${wait.wno} </td>
                    <td> ${wait.wnumber} </td>        
                    <td> ${wait.wcount} </td>
                </tr>`

    } // for end
    waitingTbody.innerHTML = html;
} // func end
waitPrint(); // 최초 초기화