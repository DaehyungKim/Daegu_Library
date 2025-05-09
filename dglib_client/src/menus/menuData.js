export const menuItems = [
    {
        id: 'about',
        link: '/about',
        subItems: [
            { title: '인사말', link: '/about/greeting' },
            { title: '연혁', link: '/about/history' },
            { title: '조직도', link: '/about/organization' }
        ]
    },
    {
        id: 'books',
        title: '도서 정보',
        link: '/none',
        subItems: [
            { title: '신착도서', link: '/books/new' },
            { title: '베스트도서', link: '/books/best' },
            { title: '추천도서', link: '/books/recommended' }
        ]
    },
    {
        id: 'usage',
        title: '도서관 이용',
        link: '/none',
        subItems: [
            { title: '이용시간', link: '/usage/hours' },
            { title: '대출/반납', link: '/usage/borrow' },
            { title: '시설안내', link: '/usage/facilities' }
        ]
    },
    {
        id: 'reservation',
        title: '신청 및 예약',
        link: '/none',
        subItems: [
            { title: '도서 예약', link: '/reservation/book' },
            { title: '좌석 예약', link: '/reservation/seat' },
            { title: '프로그램 신청', link: '/reservation/program' }
        ]
    },
    {
        id: 'community',
        title: '시민 참여',
        link: '/none',
        subItems: [
            { title: '공지사항', link: '/community/notice' },
            { title: '자유게시판', link: '/community/board' },
            { title: '묻고 답하기', link: '/community/qna' }
        ]
    },
    {
        id: 'mylib',
        title: '내 서재',
        link: '/none',
        subItems: [
            { title: '대출현황', link: '/mylib/status' },
            { title: '예약현황', link: '/mylib/reservations' },
            { title: '관심도서', link: '/mylib/favorites' }
        ]
    }
];