import { NavLink } from 'react-router-dom';
import { useState } from 'react';
import MenuItem from './MenuItem';
import SubMenuSection from './SubMenuSection';
import { menuItems } from './menuData';

const MainMenu = () => {
    const [showAllSubmenus, setShowAllSubmenus] = useState(false);

    return (
        <nav>
            <div
                className="relative"
                onMouseEnter={() => setShowAllSubmenus(true)}
                onMouseLeave={() => setShowAllSubmenus(false)}
            >
                <ul className="flex items-center justify-center space-x-6">
                    {menuItems.map((item) => (
                        <MenuItem key={item.id} item={item} />
                    ))}
                </ul>

                {showAllSubmenus && <SubMenuSection menuItems={menuItems} />}
            </div>
        </nav>
    );
};

export default MainMenu;