package com.dglib.service.books;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dglib.dto.BookDto;
import com.dglib.dto.BookRegistrationDto;
import com.dglib.dto.BookSummaryDto;
import com.dglib.dto.LibraryBookDto;
import com.dglib.entity.Book;
import com.dglib.entity.LibraryBook;
import com.dglib.repository.BookRepository;
import com.dglib.repository.LibraryBookRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final LibraryBookRepository libraryBookRepository;
	
	
	
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
		Page<Book> books = bookRepository.findAll(pageable);
		return books.map(book -> modelMapper.map(book, BookSummaryDto.class));
	}
	
	
	

}
