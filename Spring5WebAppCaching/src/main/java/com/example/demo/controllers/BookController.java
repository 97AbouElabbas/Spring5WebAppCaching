package com.example.demo.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Book;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.services.BookService;


@Controller
@RestController
@RequestMapping("api/v1/")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/books")
	public List<Book> getBooks() {
		List<Book> list = bookService.getAllBooks();
		return list;
	}
	
	@GetMapping("/books/{id}")
	public Book getBookById(@PathVariable int id) {
		return bookService.getBookById(id);
	}
	
	@PostMapping("/books")
	public void addBook(@RequestBody Book book) {
		bookService.addBook(book);;
	}
	
	@PutMapping("/books/{id}")
	public Book updateBook(@RequestBody Book newbook, @PathVariable int id) {
		return bookService.updateBook(newbook, id);
	}
	
	@DeleteMapping("/books/{id}")
	public void deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
	}
}
