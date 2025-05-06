package com.dglib.dto;

import java.util.List;

import lombok.Data;

@Data
public class BookRegistrationDto {
    private BookDto book;
    private List<LibraryBookDto> libraryBooks;

}
