import { NavLink } from 'react-router-dom';

const MainMenu = () => {

    return(
        <ul className="flex items-center justify-center space-x-6">
        <li><NavLink to="/about" className="hover:text-yellow-400">도서관 소개</NavLink></li>
        <li><NavLink to="/none" className="hover:text-yellow-400">도서 정보</NavLink></li>
        <li><NavLink to="/none" className="hover:text-yellow-400">도서관 이용</NavLink></li>
        <li><NavLink to="/none" className="hover:text-yellow-400">신청 및 예약</NavLink></li>
        <li><NavLink to="/none" className="hover:text-yellow-400">시민 참여</NavLink></li>
        <li><NavLink to="/none" className="hover:text-yellow-400">내 서재</NavLink></li>
      </ul>
    );
    
    }
    
    export default MainMenu;