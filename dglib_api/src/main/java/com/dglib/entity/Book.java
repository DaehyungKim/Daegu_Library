package com.dglib.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 13)
	String isbn;  // ISBN 0으로 시작 가능해서 String으로 설정
	
	@Column(nullable = false, length = 150)
	String bookName;
	
	@Column(nullable = false, length = 100)
	String author;
	
	@Column(nullable = false, length = 100)
	String publisher;
	
	@Column(nullable = false)
	LocalDateTime publishDate;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String bookDescription;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String bookImageUrl;
	
	
	
	

}
