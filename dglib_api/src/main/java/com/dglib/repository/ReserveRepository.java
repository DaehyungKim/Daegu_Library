package com.dglib.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.dglib.entity.Reserve;
import com.dglib.entity.ReserveState;

public interface ReserveRepository extends JpaRepository<Reserve, Long>{
	int countByLibraryBookLibraryBookIdAndState(Long libraryBookId, ReserveState state);
	boolean existsByLibraryBookLibraryBookIdAndMemberIdAndState(Long libraryBookId, String id, ReserveState state);
	
	@EntityGraph(attributePaths = {"member", "libraryBook", "libraryBook.book", "libraryBook.reserves"})
	Page<Reserve> findAll(Pageable pageable);
	
	
}
