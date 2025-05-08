package com.dglib.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.dglib.entity.RentalState;
import com.dglib.entity.ReserveState;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ReserveBookListDto {
	
	private Long reserveId;
	private LocalDateTime reserveDate;
	private boolean isUnmanned;
	private ReserveState state;
	private String id;
	private String bookTitle;
	private String libraryBookId;
	private String author;
	private String isbn;
	private Integer reservationRank;

}
