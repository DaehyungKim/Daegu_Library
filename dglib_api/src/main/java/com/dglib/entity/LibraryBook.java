package com.dglib.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryBook {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long libraryBookId;
	
	@Column(length = 30, nullable = false)
	String callSign;
	
	@Column(length = 10, nullable = false)
	String location;
	
	@ManyToOne
	@JoinColumn(name = "isbn", nullable = false)
	Book book;

}
