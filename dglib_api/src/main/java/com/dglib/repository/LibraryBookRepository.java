package com.dglib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dglib.entity.LibraryBook;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Long> {
	

}
