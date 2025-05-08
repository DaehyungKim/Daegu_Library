package com.dglib.service.books;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dglib.dto.BookDetailDto;
import com.dglib.dto.BookDto;
import com.dglib.dto.BookRegistrationDto;
import com.dglib.dto.BookSummaryDto;
import com.dglib.dto.LibraryBookDto;
import com.dglib.dto.RentalBookListDto;
import com.dglib.dto.ReserveBookListDto;
import com.dglib.dto.ReserveStateChangeDto;
import com.dglib.entity.Book;
import com.dglib.entity.LibraryBook;
import com.dglib.entity.Member;
import com.dglib.entity.Rental;
import com.dglib.entity.RentalState;
import com.dglib.entity.Reserve;
import com.dglib.entity.ReserveState;
import com.dglib.repository.BookRepository;
import com.dglib.repository.LibraryBookRepository;
import com.dglib.repository.MemberRepository;
import com.dglib.repository.RentalRepository;
import com.dglib.repository.ReserveRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	private final BookRepository bookRepository;
	private final ModelMapper modelMapper;
	private final LibraryBookRepository libraryBookRepository;
	private final RentalRepository rentalRepository;
	private final ReserveRepository reserveRepository;
	private final MemberRepository memberRepository;
	
	
	
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
	@Transactional(readOnly = true)
	public Page<BookSummaryDto> getBookList(Pageable pageable) {
		return libraryBookRepository.findBookSummaries(pageable);
		
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public BookDetailDto getLibraryBookDetail(Long libraryBookId) {
		return libraryBookRepository.findById(libraryBookId).map(libraryBook -> {
			BookDetailDto dto = new BookDetailDto();
			String id = "kdh3218";
			boolean isRented = rentalRepository.findByLibraryBookLibraryBookIdAndStateNot(libraryBookId, RentalState.RETURNED).isPresent();
			dto.setRented(isRented);
			int reserveCount = 0;
			boolean alreadyReservedByMember = false;
			if (isRented) {
				reserveCount = reserveRepository.countByLibraryBookLibraryBookIdAndState(libraryBookId, ReserveState.RESERVED);
				alreadyReservedByMember = reserveRepository.existsByLibraryBookLibraryBookIdAndMemberIdAndState(libraryBookId, id, ReserveState.RESERVED);
			}
			dto.setAlreadyReservedByMember(alreadyReservedByMember);
			dto.setReserveCount(reserveCount);
			modelMapper.map(libraryBook.getBook(), dto);
			modelMapper.map(libraryBook, dto);
			return dto;
		}).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<RentalBookListDto> getRentalList(Pageable pageable) {
		Page<Rental> rentalList = rentalRepository.findAll(pageable);
		return rentalList.map(rental -> {
			RentalBookListDto dto = new RentalBookListDto();
			modelMapper.map(rental.getLibraryBook(), dto);
			modelMapper.map(rental.getLibraryBook().getBook(), dto);
			modelMapper.map(rental.getMember(), dto);
			modelMapper.map(rental, dto);
			return dto;
		});
	}
	
	@Override
	public void reserveBook(Long libraryBookId, String id) {
		LibraryBook libraryBook = libraryBookRepository.findById(libraryBookId).orElse(null);
		Member member = memberRepository.findById(id).orElse(null);
		
		Reserve reserve = new Reserve();
	    reserve.setLibraryBook(libraryBook);
	    reserve.setMember(member);
	    reserve.setReserveDate(LocalDateTime.now());
	    reserve.setState(ReserveState.RESERVED);
	    reserve.setUnmanned(false);
	    reserveRepository.save(reserve);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<ReserveBookListDto> getReserveList(Pageable pageable) {
		Page<Reserve> reserveList = reserveRepository.findAll(pageable);
		return reserveList.map(reserve -> {
			ReserveBookListDto dto = new ReserveBookListDto();
			LibraryBook libraryBook = reserve.getLibraryBook();
			
            modelMapper.map(reserve.getLibraryBook(), dto);
            modelMapper.map(reserve.getLibraryBook().getBook(), dto);
            modelMapper.map(reserve.getMember(), dto);
            modelMapper.map(reserve, dto);
            
            Integer rank = null;
            if (reserve.getState() == ReserveState.RESERVED) {
            	List<Reserve> allReservationsForThisBook = libraryBook.getReserves();
            	List<Reserve> reservedOnlyList = allReservationsForThisBook.stream()
                        .filter(r -> r.getState() == ReserveState.RESERVED) 
                        .collect(Collectors.toList());
            	 for (int i = 0; i < reservedOnlyList.size(); i++) {
                     if (reservedOnlyList.get(i).getReserveId().equals(reserve.getReserveId())) {
                         rank = i + 1; 
                         break;
                     }
            	 }
            }
            
            dto.setReservationRank(rank);
            return dto;
		});
	}
	@Override
	public void cancelReserveBook(List<ReserveStateChangeDto> reserveStateChangeDtos) {
	    for (ReserveStateChangeDto dto : reserveStateChangeDtos) {
	        Long reserveId = dto.getReserveId();
	        ReserveState newState = dto.getState();

	        Reserve reserve = reserveRepository.findById(reserveId)
	            .orElseThrow(() -> new EntityNotFoundException("해당 예약 ID를 찾을 수 없습니다: " + reserveId));

	        reserve.changeState(ReserveState.CANCELED);
	    }
	}
	
	@Override
	public void reReserveBook(List<ReserveStateChangeDto> reserveStateChangeDtos) {
		for (ReserveStateChangeDto dto : reserveStateChangeDtos) {
			Long reserveId = dto.getReserveId();
			ReserveState newState = dto.getState();
			Reserve reserve = reserveRepository.findById(reserveId)
		            .orElseThrow(() -> new EntityNotFoundException("해당 예약 ID를 찾을 수 없습니다: " + reserveId));
			reserve.changeState(ReserveState.RESERVED);		
		}
		
		

	}
	
	@Override
	public void completeBorrowing(List<ReserveStateChangeDto> reserveStateChangeDtos) {
		for (ReserveStateChangeDto dto : reserveStateChangeDtos) {
			Long reserveId = dto.getReserveId();
			ReserveState newState = dto.getState();
			Integer rank = dto.getReservationRank();
			if (rank != null && rank > 1) {
				throw new IllegalStateException("예약 우선 순위가 충족되지 않아 대출을 완료할 수 없습니다.");
			}
			if (newState == ReserveState.CANCELED) {
                throw new IllegalStateException("취소된 예약은 대출 완료로 변경할 수 없습니다.");
            }
			Reserve reserve = reserveRepository.findById(reserveId)
		            .orElseThrow(() -> new EntityNotFoundException("해당 예약 ID를 찾을 수 없습니다: " + reserveId));
			reserve.changeState(ReserveState.BORROWED);
			Rental rental = new Rental();
			rental.setLibraryBook(reserve.getLibraryBook());
			rental.setMember(reserve.getMember());
			rental.setRentStartDate(LocalDate.now());
			rental.setDueDate(LocalDate.now().plusDays(7));
			rental.setState(RentalState.BORROWED);
			rentalRepository.save(rental);
		}
	}	
}
