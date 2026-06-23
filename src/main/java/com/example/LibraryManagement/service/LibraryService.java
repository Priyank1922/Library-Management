package com.example.LibraryManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LibraryManagement.dto.BookTransaction;
import com.example.LibraryManagement.entity.Library;
import com.example.LibraryManagement.repository.LibraryRepository;

@Service
public class LibraryService {

	private final LibraryRepository repo;

	public LibraryService(LibraryRepository repo) {
		this.repo = repo;
	}

	public List<Library> getAllBooks() {
		return repo.findAll();
	}

	public Library getBook(Long id) {
		return repo.findById(id).orElseThrow(() -> new RuntimeException("Book Not Found"));
	}

	public Library addBook(Library library) {
		return repo.save(library);
	}

	public Library updateBook(Long id, Library updatedBook) {

		Library book = getBook(id);

		book.setBookName(updatedBook.getBookName());
		book.setTotalBooks(updatedBook.getTotalBooks());
		book.setAvailableBooks(updatedBook.getAvailableBooks());

		return repo.save(book);
	}

	public void deleteBook(Long id) {
		repo.deleteById(id);
	}

	
	//book issue code;;;;;;
	@Transactional
	public String issueBook(BookTransaction transaction) {

		Library book = repo.findById(transaction.bookId()).orElseThrow(() -> new RuntimeException("Book Not Found"));

		if (book.getAvailableBooks() < transaction.quantity()) {
			throw new RuntimeException("Insufficient Books Available");
		}

		book.setAvailableBooks(book.getAvailableBooks() - transaction.quantity());

		repo.save(book);

		return "Book Issue Successfully";
	}

	
	//book return code;;;;::
	@Transactional
	public String returnBook(BookTransaction transaction) {

		Library book = repo.findById(transaction.bookId()).orElseThrow(() -> new RuntimeException("Book Not Found"));

		if (book.getAvailableBooks() + transaction.quantity() > book.getTotalBooks()) {

			throw new RuntimeException("Returned books exceed total books");
		}

		book.setAvailableBooks(book.getAvailableBooks() + transaction.quantity());

		repo.save(book);

		return "Book Returned Successfully";
	}
}