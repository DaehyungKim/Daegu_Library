import MainMenu from "../menus/MainMenu";
import LoginMenu from "../menus/LoginMenu";
import { Link } from "react-router-dom";

const Header = () => {

return(
<>
<div className="flex justify-end mr-10 mt-3"> <LoginMenu /> </div>
<Link to = "/"> <img src="logo.png" className="w-35 ml-3" /> </Link>
<div className="mb-3"> <MainMenu /> </div>
</>
);

}

export default Header;