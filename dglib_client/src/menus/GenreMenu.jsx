import { NavLink } from "react-router-dom";

const GenreMenu = () => {

    const getNavLinkClass = ({ isActive }) => {
        return isActive
            ? "text-blue-600 font-bold underline"
            : "text-gray-700 hover:text-blue-500";
    };

    return (
        <div className="border-b border-gray-300 p-4 mb-4">
            <ul className="flex space-x-6 justify-center">
                <li><NavLink to={'/main/literature'} className={getNavLinkClass}>문학</NavLink></li>
                <li><NavLink to={'/main/philosophy'} className={getNavLinkClass}>철학</NavLink></li>
                <li><NavLink to={'/main/religion'} className={getNavLinkClass}>종교</NavLink></li>
                <li><NavLink to={'/main/history'} className={getNavLinkClass}>역사</NavLink></li>
                <li><NavLink to={'/main/language'} className={getNavLinkClass}>언어</NavLink></li>
                <li><NavLink to={'/main/art'} className={getNavLinkClass}>예술</NavLink></li>
                <li><NavLink to={'/main/social-sciences'} className={getNavLinkClass}>사회과학</NavLink></li>
                <li><NavLink to={'/main/natural-sciences'} className={getNavLinkClass}>자연과학</NavLink></li>
                <li><NavLink to={'/main/technology'} className={getNavLinkClass}>기술과학</NavLink></li>
            </ul>
        </div>
    );
}

export default GenreMenu;