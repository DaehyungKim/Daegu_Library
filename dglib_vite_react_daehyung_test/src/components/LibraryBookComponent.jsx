import { useParams } from 'react-router-dom';
import { getLibraryBookDetail } from '../api/bookApi';
import { useEffect, useState } from 'react';

const LibraryBookComponent = () => {
    const { isbn } = useParams();
    const [libraryBookDetail, setLibraryBookDetail] = useState({});
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const getBookDetails = async () => {
            setIsLoading(true);
            const response = await getLibraryBookDetail(isbn);
            setLibraryBookDetail(response);
            setIsLoading(false);
        };

        getBookDetails();
    }, [isbn]);

    if (isLoading) {
        return (
            <div className="flex justify-center items-center h-64">
                <div className="animate-spin rounded-full h-16 w-16 border-t-2 border-b-2 border-blue-500"></div>
            </div>
        );
    }

    return (
        <div className="max-w-4xl mx-auto p-8">
            <div className="flex gap-8">
                {/* 왼쪽: 이미지 섹션 */}
                <div className="w-1/3">
                    <img
                        src={libraryBookDetail.cover}
                        alt={libraryBookDetail.title}
                        className="w-full rounded-lg shadow-lg object-contain"
                    />
                </div>

                {/* 오른쪽: 도서 정보 섹션 */}
                <div className="w-2/3">
                    <h1 className="text-3xl font-bold mb-4">{libraryBookDetail.title}</h1>

                    <div className="space-y-4">
                        <div className="flex border-b border-gray-200 py-2">
                            <span className="w-24 font-semibold text-gray-600">저자</span>
                            <span>{libraryBookDetail.author}</span>
                        </div>

                        <div className="flex border-b border-gray-200 py-2">
                            <span className="w-24 font-semibold text-gray-600">출판사</span>
                            <span>{libraryBookDetail.publisher}</span>
                        </div>

                        <div className="flex border-b border-gray-200 py-2">
                            <span className="w-24 font-semibold text-gray-600">출판일</span>
                            <span>{libraryBookDetail.pubDate}</span>
                        </div>

                        <div className="flex border-b border-gray-200 py-2">
                            <span className="w-24 font-semibold text-gray-600">ISBN</span>
                            <span>{libraryBookDetail.isbn}</span>
                        </div>

                        <div className="flex border-b border-gray-200 py-2">
                            <span className="w-24 font-semibold text-gray-600">자료위치</span>
                            <span>{libraryBookDetail.location || "위치 정보 없음"}</span>
                        </div>

                        <div className="flex border-b border-gray-200 py-2">
                            <span className="w-24 font-semibold text-gray-600">청구기호</span>
                            <span>{libraryBookDetail.callSign || "청구기호 정보 없음"}</span>
                        </div>
                    </div>

                    <div className="mt-8">
                        <h2 className="text-xl font-bold mb-4">도서 소개</h2>
                        <p className="text-gray-700 leading-relaxed whitespace-pre-line">
                            {libraryBookDetail.description || "설명이 없습니다."}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default LibraryBookComponent;