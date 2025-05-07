package com.dglib.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookDto {
	private String bookTitle;
    private String author;
    private String publisher;
    private LocalDate pubDate;
    private String isbn;
    private String description;
    private String cover;

}
