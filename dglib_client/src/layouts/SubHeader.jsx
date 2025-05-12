import { Link } from "react-router-dom";

const SubHeader = ( {subTitle, mainTitle} ) => {
    return (
        <div className="flex w-full justify-between items-center">
            <div className="text-left px-4 py-4">{subTitle}</div>
            <div className="flex items-center">
                <Link to = "/"> <img src="/home.png" className="w-8 h-8 ml-3" /> </Link>

                <div> <img src="/share.png" className="w-8 h-8 ml-3" /> </div>
                <div> <img src="/print.png" className="w-8 h-8 ml-3 mr-4" /> </div>
            </div>
        </div>
    );
}

export default SubHeader;