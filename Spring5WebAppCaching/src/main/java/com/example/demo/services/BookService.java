package com.example.demo.services;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Book;
import com.example.demo.exceptions.BookNotFoundException;
import com.example.demo.repo.BookRepo;

@Service
public class BookService {
	
	private final BookRepo bookRepo;
	
	public BookService(BookRepo bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
	
	@Cacheable(value="books", key = "#id")
	public Book getBookById(int id) {
		return bookRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
	}
	
	public void addBook(Book book) {
		bookRepo.save(book);
	}
	
	@CachePut(value = "books", key = "#id")
	public Book updateBook(Book newbook, int id) {
		return bookRepo.findById(id).map(book -> {
			book.setTitle(newbook.getTitle());
			book.setIsbn(newbook.getIsbn());
			return bookRepo.save(book);
		}).orElseGet(() -> {
			newbook.setId(id);
			return bookRepo.save(newbook);
		});
	}
	
	@CacheEvict(value = "books", key = "#id")
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
	}
}
