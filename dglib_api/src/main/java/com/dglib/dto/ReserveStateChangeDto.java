package com.dglib.dto;

import java.util.List;

import com.dglib.entity.ReserveState;

import lombok.Data;

@Data
public class ReserveStateChangeDto {
	private Long reserveId;
	private Integer reservationRank;
	private ReserveState state;

}
