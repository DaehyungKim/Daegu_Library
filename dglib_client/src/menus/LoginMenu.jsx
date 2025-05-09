import { NavLink } from 'react-router-dom';
import { useRecoilState } from "recoil"
import RecoilLoginState from '../atoms/loginState';

const LoginMenu = () => {

    const [loginState, setLoginState ] = useRecoilState(RecoilLoginState);

    return(
        <ul className="flex items-center justify-center space-x-6">
        {
        loginState.id ? 
        <>
        <li><NavLink to="/logout" className="hover:text-yellow-400">로그아웃</NavLink></li>
        <li><NavLink to="/mypage" className="hover:text-yellow-400">마이페이지</NavLink></li>
        </>
        :
        <>
        <li><NavLink to="/login" className="hover:text-yellow-400">로그인</NavLink></li>
        <li><NavLink to="/signup" className="hover:text-yellow-400">회원가입</NavLink></li>
        </>
        }
      </ul>
    );
    
    }
    
    export default LoginMenu;