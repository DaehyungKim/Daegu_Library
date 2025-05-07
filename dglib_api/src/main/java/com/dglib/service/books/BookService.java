package com.dglib.service.books;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dglib.dto.BookDetailDto;
import com.dglib.dto.BookRegistrationDto;
import com.dglib.dto.BookSummaryDto;
import com.dglib.dto.RentalDto;

public interface BookService {
	
	void registerBook(BookRegistrationDto bookRegistrationDto);
	Page<BookSummaryDto> getBookList(Pageable pageable);
	BookDetailDto getLibraryBookDetail(Long libraryBookId);
	Page<RentalDto> getRentalList(Pageable pageable);
	
     

}
