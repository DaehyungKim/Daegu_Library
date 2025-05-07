import { getLibraryBookList } from "../api/bookApi";
import { useEffect, useState } from "react";

const LibraryBookListComponent = () => {
    const [books, setBooks] = useState([]);
    const [totalElements, setTotalElements] = useState(0);
    const [isLoading, setIsLoading] = useState(false);
    useEffect(() => {
        const getbookList = async () => {
            const response = await getLibraryBookList();
            console.log(response);
            setBooks(response.content);
            setIsLoading(false);
            console.log(response.totalPages)
            console.log(response.totalElements)
            setTotalElements(response.totalElements);
        }


        getbookList();
    }, []);

    if (isLoading) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-blue-500"></div>
            </div>
        );
    }



    return (
        <div className="container mx-auto px-4 py-8">
            <h2 className="text-3xl font-bold mb-8 text-center">도서 목록</h2>
            <div>총 도서 {totalElements}</div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-8">
                {books.map((book, index) => (
                    <div key={index} className="bg-white rounded-lg shadow-lg overflow-hidden hover:shadow-xl transition-shadow duration-300">
                        <div className="relative h-64">
                            <img
                                src={book.cover}
                                alt={book.title}
                                className="w-full h-full object-contain"
                            />
                        </div>
                        <div className="p-6">
                            <h3 className="text-xl font-semibold mb-2 line-clamp-2 h-14">
                                {book.title}
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
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default LibraryBookListComponent;