package com.example.LibraryManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.LibraryManagement.dto.BookTransaction;
import com.example.LibraryManagement.entity.Library;
import com.example.LibraryManagement.service.LibraryService;

@RestController
@RequestMapping("/library")
public class LibraryController {
	@Autowired
	public LibraryService service;

	@GetMapping
	public List<Library> getAllBooks() {
		return service.getAllBooks();
	}

	@GetMapping("/{id}")
	public Library getBook(@PathVariable Long id) {
		return service.getBook(id);
	}

	@PostMapping
	public Library addBook(@RequestBody Library library) {
		return service.addBook(library);
	}

	@PutMapping("/{id}")
	public Library updateBook(@PathVariable Long id, @RequestBody Library library) {

		return service.updateBook(id, library);
	}

	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		service.deleteBook(id);
	}

	@PostMapping("/issue")
	public String issueBook(@RequestBody BookTransaction transaction) {

		return service.issueBook(transaction);
	}

	@PostMapping("/return")
	public String returnBook(@RequestBody BookTransaction transaction) {

		return service.returnBook(transaction);
	}
}