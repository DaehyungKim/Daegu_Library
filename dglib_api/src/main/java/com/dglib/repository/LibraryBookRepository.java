package com.dglib.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dglib.dto.BookSummaryDto;
import com.dglib.entity.LibraryBook;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
	
	
	@Query("SELECT new com.dglib.dto.BookSummaryDto(" +
		       "b.bookTitle, " +                
		       "b.author, " +              
		       "b.publisher, " +           
		       "b.pubDate, " +      
		       "b.cover, " +               
		       "lb.location, " +            
		       "lb.callSign, " +            
		       "b.isbn, " +                 
		       "lb.libraryBookId, " +       
		       "CASE WHEN EXISTS (SELECT 1 FROM Rental r WHERE r.libraryBook.libraryBookId = lb.libraryBookId AND r.state != com.dglib.entity.RentalState.RETURNED) THEN true ELSE false END" + 
		       ") " +
		       "FROM LibraryBook lb JOIN lb.book b")
	Page<BookSummaryDto> findBookSummaries(Pageable pageable);
	

}
