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
	@Column(length = 13)
	String isbn;  // ISBN 0으로 시작 가능해서 String으로 설정
	
	@Column(nullable = false, length = 150)
	String title;
	
	@Column(nullable = false, length = 100)
	String author;
	
	@Column(nullable = false, length = 100)
	String publisher;
	
	@Column(nullable = false)
	String pubDate;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String description;
	
	@Column(nullable = false, columnDefinition = "TEXT")
	String cover;
	
	
	
	

}
