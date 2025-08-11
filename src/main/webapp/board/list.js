// (*) 연동 확인
console.log("list XXOK");

// [1] 전체조회
const boardPrint = async () => {
    console.log('boardPrint XXOK');
    // 1. fetch option 중 GET 생략가능
    const response = await fetch("/board");
    // 2. 응답자료 타입변환
    const data = await response.json();
    // 3. table마크업 tbody선택자
    const boardTbody = document.querySelector('#boardTbody');
    // 4. 무엇을
    let html = ``;
    for(let i = 0; i < data.length; i++){
        const board = data[i];
        html += `<tr>
                    <td> ${board.bno} <td>
                    <td>
                        <a href="/board/view.jsp?bno=${board.bno}">
                        ${board.bcontent} 
                        </a> 
                    <td>
                    <td> ${board.bwriter} <td>                    
                </th>`
    }

    // 5. 출력
    boardTbody.innerHTML = html;

} // func end
boardPrint();
