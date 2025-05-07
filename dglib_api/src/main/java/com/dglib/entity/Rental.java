package com.dglib.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rental {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rentId;
	
	@Column(nullable = false)
	private LocalDate rentStartDate;
	
	@Column(nullable = false)
	private LocalDate dueDate;
	
	@Column(nullable = true)
	private LocalDate returnDate;
	
	@Column(nullable = true, columnDefinition = "boolean default false")
	private boolean isUnmanned;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RentalState state;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = false)
	private Member member;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "LibraryBookId", nullable = false)
	private LibraryBook libraryBook;
	
	

}
