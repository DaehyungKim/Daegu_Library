import { NavLink } from 'react-router-dom';

const MenuItem = ({ item }) => {
    return (
        <li className="relative py-3">
            <NavLink
                to={item.link}
                className="hover:text-yellow-400 block"
            >
                {item.title}
            </NavLink>
        </li>
    );
};

export default MenuItem;