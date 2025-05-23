
from datetime import datetime, timedelta
from config import logger, INFO_NARU_URL, INFO_NARU_KEY, GENRE_MAP
from utils.http import safe_request
from services.aladin import get_aladin_book_info
import httpx

async def fetch_popular_books(genre: str):
    kdc = GENRE_MAP.get(genre)
    if not kdc:
        logger.error(f"알 수 없는 장르: {genre}")
        return False, {}
    
    today = datetime.today()
    one_month_ago = today - timedelta(days=30)
    start_date = one_month_ago.strftime('%Y-%m-%d')
    end_date = today.strftime('%Y-%m-%d')
    
    params = {
        'authKey': INFO_NARU_KEY,
        'startDt': start_date,
        'endDt': end_date,
        'addCode': '0;2;9',
        'kdc': kdc,
        'pageNo': 1,
        'pageSize': 5,
        'format': 'json'
    }
    
    logger.info(f"{genre} 장르의 도서 데이터 가져오기 시작")
    data, error = await safe_request(INFO_NARU_URL, params)  # await 추가
    
    if error or not data:
        logger.error(f"정보나루 API 오류: {error}")
        return False, {}
    
    try:
        items = data.get('response', {}).get('docs', [])
        result = {
            "response": {
                "docs": []
            }
        }
        
        async with httpx.AsyncClient() as client:  # 비동기 클라이언트 사용
            for item in items:
                book = item.get('doc', {})
                isbn = book.get('isbn13') or book.get("isbn10") or book.get('isbn', '')
                
                aladin_info = await get_aladin_book_info(isbn)  # get_aladin_book_info도 async로 변경 필요
                
                book['bookname'] = aladin_info['title']
                book['authors'] = aladin_info['author']
                book['bookImageURL'] = aladin_info['cover_url']
                
                result["response"]["docs"].append({"doc": book})
                logger.info(f"도서 정보 추가: {book['bookname']} - {book['authors']}")
        
        return True, result
    except Exception as e:
        logger.error(f"데이터 처리 중 예외 발생: {e}")
        return False, {}