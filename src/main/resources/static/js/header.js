// 연동 확인
console.log('header XXOk');

// [1] 내 정보 요청해서 메뉴 나누기
const myinfo = async () => {
    console.log('info XXOK');

    const logMenu = document.querySelector('#log-menu'); // 어디에
    let html = `` // 무엇을

    try {
        // fetch exe
        const option = { method: "GET" }
        const response = await fetch('/member/info' , option);
        const data = await response.json();
        console.log(data) // 콘솔 찍어 확인
        // 비로그인시 응답자료 null임. .json() 타입변환 함수에서 오류 발생하여 catch로 이동.
        // [로그인중] 정상 통신 fetch
        html += `                                
                    <li> <span> ${data.mname}님 환영합니다! </span> </li>
                    <li> <a href="/member/info.jsp"> 내정보 </a> </li>
                    <li> <a href="#" onclick="logout()"> 로그아웃 </a> </li>
                `;
    } catch {
        // [비로그인중] 비정상 통신 fetch
        html += `                
                    <li> <a href="/member/login.jsp"> 로그인 </a> </li>
                    <li> <a href="/member/signup.jsp"> 회원가입 </a> </li>
                `
    }

    logMenu.innerHTML = html; // 출력

}
myinfo(); // 초기화될 때마다 실행

// [2] 로그아웃
const logout = async() =>{
    try{
        // fetch exe
        const option = { method : "GET"}
        const response = await fetch( "/member/logout" , option );
        const data = await response.json();
        // fetch 통신 결과
        if (data == true){
            alert('로그아웃 했습니다');
            location.href="/index.jsp"; // 로그아웃 성공 시 인덱스로 이동
        } else {
            alert('비정상 요청 및 관리자 문의')
        }
    } catch {

    }
} 


/*

GET/DELETE 방식
const method = async() =>{
    try{
        const option = { method : "GET"}
        const response = await fetch( "/URL" , option );
        const data = await response.json();
    }catch{

    }
} 

POST/PUT 방식
const method = async () => {
    try{
        const option = { 
        method : "POST" ,
        headers : {"Content-Type" : "application/json" },
        body : JSON.stringfy(obj)
        
        }
        const response = await fetch( "/URL" , option );
        const data = await response.json();
    } catch {
     
    }
    
    
    }


*/
