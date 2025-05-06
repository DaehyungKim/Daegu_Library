package com.dglib.dto;

import lombok.Data;

@Data
public class BookDto {
	private String title;
    private String author;
    private String publisher;
    private String pubDate;
    private String isbn;
    private String description;
    private String cover;

}
