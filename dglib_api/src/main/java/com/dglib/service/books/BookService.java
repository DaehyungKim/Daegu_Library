package com.dglib.service.books;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dglib.dto.BookDetailDto;
import com.dglib.dto.BookRegistrationDto;
import com.dglib.dto.BookSummaryDto;
import com.dglib.dto.RentalBookListDto;
import com.dglib.dto.ReserveBookListDto;
import com.dglib.dto.ReserveStateChangeDto;

public interface BookService {
	
	void registerBook(BookRegistrationDto bookRegistrationDto);
	Page<BookSummaryDto> getBookList(Pageable pageable);
	BookDetailDto getLibraryBookDetail(Long libraryBookId);
	Page<RentalBookListDto> getRentalList(Pageable pageable);
	void reserveBook(Long libraryBookId, String id);
	Page<ReserveBookListDto> getReserveList(Pageable pageable);
	void cancelReserveBook(List<ReserveStateChangeDto> reserveStateChangeDtos);
	void reReserveBook(List<ReserveStateChangeDto> reserveStateChangeDtos);
	void completeBorrowing(List<ReserveStateChangeDto> reserveStateChangeDtos);
	
     

}
