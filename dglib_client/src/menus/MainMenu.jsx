import { useState, useRef, useEffect } from 'react';
import { menuItemsSelector } from './menuItems';
import { useRecoilValue } from 'recoil';
import { Link } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import { menuVariants, subMenuVariants, subMenuContainerVariants } from '../animations/menuAnimation';

const MainMenu = () => {
    const [isHovering, setIsHovering] = useState(false);
    const [menuWidths, setMenuWidths] = useState([]);
    const [activeMenuIndex, setActiveMenuIndex] = useState(null);
    const menuRefs = useRef([]);
    const menuItems = useRecoilValue(menuItemsSelector);
    
    useEffect(() => {
        const widths = menuRefs.current.map(ref => ref.getBoundingClientRect().width);
        setMenuWidths(widths);
    }, []);//Role 의존성추가


    return (
        <div className="relative w-full" onMouseLeave={() => {
                setIsHovering(false);
                setActiveMenuIndex(null);
            }}
        >
            <div className="flex justify-center py-3 bg-white relative">
                <div className="flex items-end">
                    {menuItems.map((menu, index) => (
                        <div 
                            key={menu.id}
                            ref={el => menuRefs.current[index] = el}
                            className="px-12 relative"
                            onMouseEnter={() => {
                                setIsHovering(true);
                                setActiveMenuIndex(index);
                            }}
                        >
                            <div className="h-full flex items-center justify-center relative">
                                <Link 
                                    to={menu.link}
                                    className={`block text-center whitespace-nowrap ${
                                        activeMenuIndex === index 
                                            ? 'text-emerald-700 font-bold scale-105' 
                                            : 'hover:text-emerald-500'
                                    }`}
                                >
                                    {menu.title}
                                </Link>
                                <AnimatePresence>
                                    {activeMenuIndex === index && (
                                        <motion.div 
                                            className="absolute bottom-[-12px] h-0.5 bg-emerald-700 z-10"
                                            initial={{ width: '0%', left: '50%' }} 
                                            animate={{ width: '100%', left: '0%' }} 
                                            exit={{ width: '0%', left: '50%' }} 
                                            transition={{ duration: 0.3, ease: "easeInOut" }} 
                                        />
                                    )}
                                </AnimatePresence>
                            </div>
                        </div>
                    ))}
                </div>
            </div>

            <div className="w-full h-[1px] bg-gray-300"></div>
            
            <AnimatePresence>
                {isHovering && (
                    <motion.div 
                        className="absolute left-0 w-full bg-white border-b border-b-gray-300 z-10 shadow-md"
                        variants={menuVariants}
                        initial="hidden"
                        animate="visible"
                        exit="exit"
                    >
                        <div className="flex justify-center">
                            {menuItems.map((menu, index) => (
                                <div 
                                    key={menu.id} 
                                    className="px-6 flex justify-center"
                                    style={{ width: `${menuWidths[index]}px` }}
                                    onMouseEnter={() => setActiveMenuIndex(index)}
                                >
                                    <motion.ul 
                                        className="py-4 text-center"
                                        initial="hidden"
                                        animate="visible"
                                        variants={subMenuContainerVariants}
                                    >
                                        {menu.subMenus.map((subMenu, subIndex) => (
                                            <motion.li 
                                                key={subIndex} 
                                                className="py-2"
                                                variants={subMenuVariants}
                                                transition={{ duration: 0.3 }}
                                            >
                                                <Link
                                                    to={subMenu.link}
                                                    className="block text-xs whitespace-nowrap hover:text-emerald-700 hover:font-bold"
                                                >
                                                    {subMenu.name}
                                                </Link>
                                            </motion.li>
                                        ))}
                                    </motion.ul>
                                </div>
                            ))}
                        </div>
                    </motion.div>
                )}
            </AnimatePresence>
        </div>
    );
};

export default MainMenu;