import Header from "./Header";
import Footer from "./Footer";
import LSide from "./LSide";

const Layout = ({children}) => {

return(
<>
<Header />
<LSide />
<main>{children}</main>
<Footer />
</>
);
}

export default Layout;