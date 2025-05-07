import { getRentalList } from "../api/bookApi";
import { useState, useEffect } from "react";

const RentListComponent = () => {
    const [rentalList, setRentalList] = useState([]);
    const [pageable, setPageable] = useState({});
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
            const getbookList = async () => {
                setIsLoading(true);
                const response = await getRentalList();
                setRentalList(response.content);
                setPageable(response);
                console.log("대여 목록:", response);
                setIsLoading(false);
            }
            getbookList();
        }, []);
    const pageClick = async (page) => {
        if (page - 1 === pageable.pageable.pageNumber) return;
        setIsLoading(true);
        const response = await getRentalList(page);
        setRentalList(response.content);
        setPageable(response);
        setIsLoading(false);
    };



    if (isLoading && !rentalList.length) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-blue-500"></div>
            </div>
        );
    }

    return (
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
            <h1 className="text-3xl font-bold mb-8 text-center">대출 목록</h1>
            {isLoading && rentalList.length > 0 && ( //
                <div className="absolute inset-0 bg-white bg-opacity-75 flex justify-center items-center z-10">
                    <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-blue-500"></div>
                </div>
            )}
            <div className="shadow-md rounded-lg overflow-x-auto">
                <table className="min-w-full bg-white">
                    <thead className="bg-blue-500 text-white">
                        <tr>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">회원ID</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">도서명</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">저자</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">ISBN</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">대여일</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">반납예정일</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">반납일</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">상태</th>
                            <th className="py-3 px-6 text-left text-sm font-semibold uppercase">연체 여부</th>
                        </tr>
                    </thead>
                    <tbody className="text-gray-700">
                        {!isLoading && rentalList.length === 0 ? (
                            <tr>
                                <td colSpan="6" className="py-10 px-6 text-center text-gray-500 text-xl">
                                    대여한 도서가 없습니다.
                                </td>
                            </tr>
                        ) : (
                            rentalList.map((item, index) => {
                                const today = new Date();
                                const dueDate = new Date(item.dueDate);
                                today.setHours(0, 0, 0, 0);
                                dueDate.setHours(0, 0, 0, 0);

                                const isOverdue = item.state === "BORROWED" && dueDate < today;

                                return (
                                    <tr key={index} className={`border-b border-gray-200 hover:bg-gray-100 transition-colors duration-200 ${isOverdue ? 'bg-red-50' : ''}`}>
                                        <td className="py-4 px-6">{item.id}</td>
                                        <td className="py-4 px-6">{item.bookTitle}</td>
                                        <td className="py-4 px-6">{item.author}</td>
                                        <td className="py-4 px-6 whitespace-nowrap">{item.isbn}</td>
                                        <td className="py-4 px-6 whitespace-nowrap">{item.rentStartDate}</td>
                                        <td className="py-4 px-6 whitespace-nowrap">{item.dueDate}</td>
                                        <td className="py-4 px-6 whitespace-nowrap">{item.returnDate || '-'}</td>
                                        <td className="py-4 px-6 whitespace-nowrap">
                                            <span className={`px-2 py-1 text-xs font-semibold rounded-full ${
                                                item.state === "BORROWED" ? (isOverdue ? "bg-red-200 text-red-800" : "bg-yellow-200 text-yellow-800") :
                                                item.state === "RETURNED" ? "bg-green-200 text-green-800" : "bg-gray-200 text-gray-800"
                                            }`}>
                                                {item.state === "BORROWED" ?  "대여중" : "반납완료"}
                                            </span>
                                        </td>
                                        <td className="py-4 px-6 whitespace-nowrap">
                                            {isOverdue ? (
                                                <span className="text-red-600 font-semibold">연체중</span>
                                            ) : (
                                                <span>-</span>
                                            )}
                                        </td>
                                    </tr>
                                );
                            })
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default RentListComponent;