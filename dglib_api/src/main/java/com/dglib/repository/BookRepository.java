package com.dglib.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.dglib.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
	

}
