/**
 * 연락처 중복 및 유효성 검사를 수행하는 재사용 가능한 함수
 * @param {string} phoneNumber - 검사할 연락처 문자열
 * @returns {Promise<{isValid: boolean, message: string}>} - 유효성 검사 결과 객체를 담은 프로미스
 */
async function validatePhoneNumber(phoneNumber) {
    // 1. 유효성 검사 (길이 및 하이픈 포함 여부)
    if (phoneNumber.length !== 13 || !phoneNumber.includes('-')) {
        return { isValid: false, message: '-(하이픈) 포함한 13글자 연락처 입력해주세요.' };
    }

    // 2. 유효성 검사 (서버 중복 확인)
    try {
        const response = await fetch(`/member/check?type=mphone&data=${phoneNumber}`);
        if (!response.ok) {
            // 서버 에러 처리
            return { isValid: false, message: '서버 통신 오류. 잠시 후 다시 시도해주세요.' };
        }
        const isDuplicate = await response.json();

        if (isDuplicate) {
            return { isValid: false, message: '현재 사용중인 연락처입니다.' };
        } else {
            return { isValid: true, message: '사용 가능한 연락처입니다.' };
        }
    } catch (error) {
        console.error('Phone validation error:', error);
        return { isValid: false, message: '연락처 검사 중 오류가 발생했습니다.' };
    }
}
