package com.dglib.dto;

import java.time.LocalDate;

import com.dglib.entity.LibraryBook;
import com.dglib.entity.Member;
import com.dglib.entity.RentalState;

import lombok.Data;


@Data
public class RentalBookListDto {

	private LocalDate rentStartDate;
	private LocalDate dueDate;
	private LocalDate returnDate;
	private boolean isUnmanned;
	private RentalState state;
	private String id;
	private String bookTitle;
	private String author;
	private String isbn;
	private String libraryBookId;
	

}
