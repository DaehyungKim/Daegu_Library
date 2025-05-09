import { atom, selector } from "recoil";

const loginState = atom({
    key:'loginState',
    default: loadCookie()
})

function loadCookie(){
    // 쿠키 읽는 부분 구현 필요
    return {};
}

export default loginState