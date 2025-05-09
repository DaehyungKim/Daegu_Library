import { Suspense, lazy } from "react";
import { createBrowserRouter } from "react-router-dom";
import Loading from "./Loading";

const Main = lazy(()=> import ("../pages/MainPage"));
const About = lazy(()=> import ("../pages/AboutPage"));
const Login = lazy(()=> import ("../pages/LoginPage"));
const None = lazy(()=> import ("../pages/NonePage"));

const root = createBrowserRouter([
    {
        path: "",
        element: <Suspense fallback={Loading}><Main /></Suspense>
    },
    {
        path: "about",
        element: <Suspense fallback={Loading}><About /></Suspense>
    },
    {
        path: "login",
        element: <Suspense fallback={Loading}><Login /></Suspense>
    },
    {
        path: "*",
        element: <Suspense fallback={Loading}><None /></Suspense>
    }
]);

export default root;