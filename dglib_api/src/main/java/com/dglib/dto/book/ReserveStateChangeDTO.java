package com.dglib.dto.book;

import java.util.List;

import com.dglib.entity.book.ReserveState;

import lombok.Data;

@Data
public class ReserveStateChangeDTO {
	private Long reserveId;
	private Integer reservationRank;
	private ReserveState state;
	private Long libraryBookId;
	private String mid;


}
