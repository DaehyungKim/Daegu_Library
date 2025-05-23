package com.dglib.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.dglib.dto.BookDto;
import com.dglib.dto.BookRegistrationDto;
import com.dglib.dto.LibraryBookDto;
import com.dglib.repository.BookRepository;
import com.dglib.repository.LibraryBookRepository;
import com.dglib.service.books.BookService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;



@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
	@Autowired
	private WebClient webClient;
	private final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	private final BookService bookService;
	
	@GetMapping("/bookreco/{genre}")
	public Mono<ResponseEntity<Map<String, String>>> bookReco(@PathVariable String genre) {
		LOGGER.info("genre: {}", genre);
		String path = "/bookreco/" + genre;
		
		return webClient.get()
				.uri(path).retrieve().bodyToMono(String.class).map(result -> {
					LOGGER.info("result: {}", result);
					Map<String, String> responseMap = new HashMap<>();
					responseMap.put("result", result);
					return ResponseEntity.ok(responseMap);
				}).onErrorResume(e -> {
					LOGGER.error("Python 백엔드 호출 중 오류 발생", e);
					Map<String, String> responseMap = new HashMap<>();
					responseMap.put("result", "백엔드 서버와 통신 중 오류가 발생했습니다.");
					return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMap));
				});
		
		

		
	}
	
	@GetMapping("/search/{searchTerm}")
	public Mono<ResponseEntity<String>> searchBookApi(@PathVariable String searchTerm,
										@RequestParam(defaultValue = "1") int page,
										@RequestParam(defaultValue = "10") int itemsPerPage) {
		LOGGER.info("검색어: {}, 페이지: {}, 페이지당 항목 수: {}", searchTerm, page, itemsPerPage);
		return webClient.get()
				.uri(uriBuilder -> uriBuilder
                        .path("/search/{search_term}")
                        .queryParam("page", page)
                        .queryParam("items_per_page", itemsPerPage)
                        .build(searchTerm))
				.retrieve()
				.bodyToMono(String.class)
				.map(body -> {
					return ResponseEntity.ok(body);
				})
				.onErrorResume(e -> {
                    LOGGER.error("Python 백엔드 호출 중 오류 발생", e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("백엔드 서버와 통신 중 오류가 발생했습니다."));
                });
	}
	
	@PostMapping("/regbook")
	public ResponseEntity<String> regBook(@RequestBody BookRegistrationDto bookRegistration) {
		bookService.registerBook(bookRegistration);
		LOGGER.info("도서 등록 성공");
		
	    
		return ResponseEntity.ok("도서가 성공적으로 등록되었습니다.");
	}
	

}
