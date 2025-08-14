console.log('update XXOK');

// [1] 회원번호, 아이디 출력
const onUpdateFind = async () => {
    try{
        const response = await fetch ('/member/info')
        const data = await response.json();
        // 정보 가져오기
        document.querySelector('.mno').innerHTML = data.mno;
        document.querySelector('.mid').innerHTML = data.mid;
    } catch (error){
        console.log(error)
        location.href = "/member/login.jsp"; // 로그인 페이지로
    }
}
onUpdateFind();

// [2] 정보 수정
const onUpdate = async () => {
    console.log('onUpdate XXOK');
    if (signPass.includes(false)) {
        alert('올바른 정보를 입력후 가능합니다.')
        return;
    }
    // 마크업 단위
    const mname = document.querySelector('.mname').value;
    const mphone = document.querySelector('.mphone').value;

    // 객체화
    const obj = {
        mname,
        mphone
    }
    // fetch
    try{
        const option = {
            method : "PUT",
            headers : {"Content-Type" : "application/json"} ,
            body : JSON.stringify(obj)
        }
        const response = await fetch ('/member/update' , option)
        const data = await response.json();
        //return
        if ( data == true ){
            alert('정보 수정 되었습니다.')
            location.href = "/index.jsp"
        } else {
            alert('정보 수정 실패했습니다.')
        }

    } catch(error) {
        console.log(error)
    }

}

// 유효성검사 체크리스트
const signPass = [null, false]; // 초기값 실패. 0인덱스 아이디체크, 1인덱스 연락처체크
// 연락처체크만 합니다
