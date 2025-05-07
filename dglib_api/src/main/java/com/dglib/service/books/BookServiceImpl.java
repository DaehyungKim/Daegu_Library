package com.dglib.service.books;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dglib.dto.BookDetailDto;
import com.dglib.dto.BookDto;
import com.dglib.dto.BookRegistrationDto;
import com.dglib.dto.BookSummaryDto;
import com.dglib.dto.LibraryBookDto;
import com.dglib.dto.RentalDto;
import com.dglib.entity.Book;
import com.dglib.entity.LibraryBook;
import com.dglib.entity.Rental;
import com.dglib.repository.BookRepository;
import com.dglib.repository.LibraryBookRepository;
import com.dglib.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final LibraryBookRepository libraryBookRepository;
	private final RentalRepository rentalRepository;
	
	
	
	@Override
	public void registerBook(BookRegistrationDto bookRegistrationDto) {
		BookDto book = bookRegistrationDto.getBook();
		List<LibraryBookDto> libraryBooks = bookRegistrationDto.getLibraryBooks();
		
		Book existingBook = bookRepository.findById(book.getIsbn()).orElse(null);
		
		if (existingBook == null) {
			existingBook = bookRepository.save(modelMapper.map(book, Book.class));
		}
		Book bookEntity = existingBook;
		List<LibraryBook> libraryBookEntities = libraryBooks.stream()
		        .map(dto -> {
		            LibraryBook entity = modelMapper.map(dto, LibraryBook.class);
		            entity.setBook(bookEntity);
		            return entity;
		        })
		        .collect(Collectors.toList());

		libraryBookRepository.saveAll(libraryBookEntities);
	}
	
	@Override
	public Page<BookSummaryDto> getBookList(Pageable pageable) {
		Page<LibraryBook> libraryBooks = libraryBookRepository.findAll(pageable);
	    return libraryBooks.map(libraryBook -> {
	        BookSummaryDto dto = new BookSummaryDto();
	        modelMapper.map(libraryBook.getBook(), dto);
	        modelMapper.map(libraryBook, dto);
	        return dto;
	    });
		
		
	}
	
	@Override
	public BookDetailDto getLibraryBookDetail(Long libraryBookId) {
		
		return libraryBookRepository.findById(libraryBookId).map(libraryBook -> {
			BookDetailDto dto = new BookDetailDto();
			modelMapper.map(libraryBook.getBook(), dto);
			modelMapper.map(libraryBook, dto);
			return dto;
		}).orElse(null);
	}
	
	@Override
	public Page<RentalDto> getRentalList(Pageable pageable) {
		Page<Rental> rentalList = rentalRepository.findAll(pageable);
		return rentalList.map(rental -> {
			RentalDto dto = new RentalDto();
			modelMapper.map(rental.getLibraryBook(), dto);
			modelMapper.map(rental.getLibraryBook().getBook(), dto);
			modelMapper.map(rental.getMember(), dto);
			modelMapper.map(rental, dto);
			return dto;
		});
	}
	
	
	
	

}
