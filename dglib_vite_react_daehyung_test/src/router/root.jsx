import { createBrowserRouter } from "react-router-dom";
import { Suspense, lazy } from "react";
import gerneRouter from "./genreRouter";
import { Navigate } from "react-router-dom";

const Loading = <div>페이지 불러오는중...</div>
const Genre = lazy(() => import("../pages/Genre"));
const Search = lazy(() => import("../pages/Search"));
const BookDetail = lazy(() => import("../pages/BookDetail"));
const BookReg = lazy(() => import("../pages/BookReg"));
const SearchBookResult = lazy(() => import("../components/SearchBookResult"));
const LibraryBookList = lazy(() => import("../pages/LibraryBookList"));
const LibraryBook = lazy(() => import("../pages/LibraryBook"));

const root = createBrowserRouter([

    {
        path : "",
        element: <Navigate to="genre" replace />
    },
    {
        path : "genre",
        element: <Suspense fallback={Loading}><Genre /></Suspense>,
        children: gerneRouter()
    },
    {
        path : "search",
        element: <Suspense fallback={Loading}><Search /></Suspense>
    },
    {
        path : "bookdetail/:isbn",
        element: <Suspense fallback={Loading}><BookDetail /></Suspense>
    },
    {
        path : "bookreg",
        element: <Suspense fallback={Loading}><BookReg /></Suspense>
    },
    {
        path : "searchbookresult",
        element: <Suspense fallback={Loading}><SearchBookResult /></Suspense>
    },
    {
        path : "librarybooklist",
        element: <Suspense fallback={Loading}><LibraryBookList /></Suspense>
    },
    {
        path : "librarybook/:isbn",
        element: <Suspense fallback={Loading}><LibraryBook /></Suspense>

    }

])

export default root;