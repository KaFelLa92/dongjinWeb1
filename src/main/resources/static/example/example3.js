console.log("example3 xxok");

// (1) 비동기 fetch
// 람다식함수 : const func1 = ( ) => { }
// 선언적함수 : function func1( ){ }
const func1 = ( ) => { 
    console.log('[1] fetch 전')
    // (fetch) 실행된 후 결과(response) 나오기 전에 다음 코드로 이동
    // JS는 응답 기다리지 않는다.
    const option = { method : "GET"}
    fetch("/day06/exam1" , option )
        .then( response => response.json())
        .then( data => { console.log(data) 
            console.log('[2] fetch 통신 결과')
        })
        .catch( error => {console.log(error)})

    console.log('[3] fetch 후')
    // 예측 : [1] , [2] , [3]
    // 결과 : [1] , [3] , [2] 순으로 콘솔 찍힘
} // func end

// (2) 동기 fetch
const func2 = async ( ) => { // async 써줘야 동기화 가능

    console.log( '[1] fetch 전')

    // (fetch)
    const option = { method : "GET" }
    try{
        const response = await fetch("/day06/exam1" , option)
        const data = await response.json();
        console.log(data);
        console.log( '[2] fetch 통신 결과');
    } catch( error ){
        console.log(error);
    }
    console.log( '[3] fetch 후');

    // 예측 : [1] , [2] , [3]
    // 결과 : [1] , [2] , [3]
}