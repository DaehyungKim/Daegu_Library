package com.dglib.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class BookSummaryDto {
	private String title;
    private String author;
    private String publisher;
    private LocalDate pubDate;
    private String cover;
	private String location;
    private String callSign;

}
