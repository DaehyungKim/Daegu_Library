import { getLibraryBookList } from "../../api/bookApi";
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { usePagination } from "../../hooks/usePagination";
import Loading from "../../routers/Loading";

const FilterBookListComponent = () => {
    const [books, setBooks] = useState([]);
    const [pageable, setPageable] = useState({});

    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const getBookList = async () => {
            setIsLoading(true);
            const response = await getLibraryBookList();
            setBooks(response.content);
            setPageable(response);
            setIsLoading(false);
            console.log(response.content);
        }
        getBookList();
    }, []);

    const pageClick = async (page) => {
        if (page - 1 === pageable.pageable.pageNumber) return;
        setIsLoading(true);
        const response = await getLibraryBookList(page);
        setBooks(response.content);
        setPageable(response);
        setIsLoading(false);
     }

     const { renderPagination } = usePagination(pageable, pageClick, isLoading);

    if (isLoading) {
        return (
            <Loading />
        );
    }
    return (
        <div className="container mx-auto px-4 py-8 max-w-3xl">
            <div className="mb-4">총 도서 {pageable.totalElements}</div>
            {isLoading && books.length > 0 && ( //
               <Loading />
            )}
            <div className="space-y-6">
            {books.map((book, index) => {
                 const libraryBookId = book.libraryBookId;


                return (
                    <Link key={index}
                        className="flex flex-col md:flex-row bg-white rounded-lg shadow-lg overflow-hidden hover:shadow-xl transition-shadow duration-300 gap-6 p-6"
                        to={`/librarybook/${libraryBookId}`}
                    >
                        <div className="w-full md:w-48 flex justify-center">
                            <img
                                src={book.cover}
                                alt={book.bookTitle}
                                className="h-64 object-contain"
                            />
                        </div>
                        <div className="flex-1">
                            <h3 className="text-xl font-semibold mb-4">
                                {book.bookTitle}
                            </h3>
                            <div className="space-y-2 text-gray-600">
                                <p className="text-sm">
                                    <span className="font-medium">저자:</span> {book.author}
                                </p>
                                <p className="text-sm">
                                    <span className="font-medium">출판사:</span> {book.publisher}
                                </p>
                                <p className="text-sm">
                                    <span className="font-medium">출판일:</span> {book.pubDate}
                                </p>
                                <p className="text-sm">
                                    <span className="font-medium">자료위치:</span> {book.location}
                                </p>
                                <p className="text-sm">
                                    <span className="font-medium">청구기호:</span> {book.callSign}
                                </p>
                                <p className="text-sm">
                                    <span className="font-medium">도서상태:</span> {book.rented ? "대출중" : "대출가능"}
                                </p>
                            </div>
                        </div>
                    </Link>
                );
            })}
            </div>
            {renderPagination()}
        </div>
    );
}

export default FilterBookListComponent;