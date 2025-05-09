import { NavLink } from 'react-router-dom';

const SubMenuSection = ({ menuItems }) => {
    return (
        <div className="absolute left-0 right-0 mt-0 bg-white shadow-md rounded-b-md border z-10 py-4 flex justify-center">
            {menuItems.map((item) => (
                item.subItems && (
                    <CategorySection key={item.id} category={item} />
                )
            ))}
        </div>
    );
};

const CategorySection = ({ category }) => {
    return (
        <div className="mx-4 min-w-[120px]">
            <ul>
                {category.subItems.map((subItem, index) => (
                    <SubMenuItem key={index} subItem={subItem} />
                ))}
            </ul>
        </div>
    );
};

const SubMenuItem = ({ subItem }) => {
    return (
        <li className="py-1 hover:bg-gray-100">
            <NavLink
                to={subItem.link}
                className="text-gray-700 hover:text-yellow-500 block whitespace-nowrap"
            >
                {subItem.title}
            </NavLink>
        </li>
    );
};

export default SubMenuSection;