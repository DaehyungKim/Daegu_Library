package com.dglib.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dglib.entity.Rental;
import com.dglib.entity.RentalState;

public interface RentalRepository extends JpaRepository<Rental, Long> {
	Optional<Rental> findByLibraryBookLibraryBookIdAndStateNot(Long libraryBookId, RentalState state);
	
	@EntityGraph(attributePaths = {"libraryBook.book", "member"})
	Page<Rental> findAll(Pageable pageable);

}
