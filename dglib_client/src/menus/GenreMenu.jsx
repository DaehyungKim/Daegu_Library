import { NavLink } from "react-router-dom";

const GenreMenu = () => {

    const getNavLinkClass = ({ isActive }) => {
        return isActive
            ? "text-black font-bold"
            : "text-gray-500 hover:text-black";
    };

    return (
        <div className="p-4">
            <ul className="flex space-x-10 ml-10">
                <li><NavLink to={'/literature'} className={getNavLinkClass}>문학</NavLink></li>
                <li><NavLink to={'/philosophy'} className={getNavLinkClass}>철학</NavLink></li>
                <li><NavLink to={'/religion'} className={getNavLinkClass}>종교</NavLink></li>
                <li><NavLink to={'/history'} className={getNavLinkClass}>역사</NavLink></li>
                <li><NavLink to={'/language'} className={getNavLinkClass}>언어</NavLink></li>
                <li><NavLink to={'/art'} className={getNavLinkClass}>예술</NavLink></li>
                <li><NavLink to={'/social-sciences'} className={getNavLinkClass}>사회과학</NavLink></li>
                <li><NavLink to={'/natural-sciences'} className={getNavLinkClass}>자연과학</NavLink></li>
                <li><NavLink to={'/technology'} className={getNavLinkClass}>기술과학</NavLink></li>
            </ul>
            <div className="w-[100%] h-[1px] bg-[#00893B] mt-4"></div>
        </div>
    );
}

export default GenreMenu;