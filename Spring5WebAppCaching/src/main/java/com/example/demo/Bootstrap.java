package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Book;
import com.example.demo.repo.BookRepo;


@Component
public class Bootstrap implements CommandLineRunner{
	
	private final BookRepo bookRepo;
	
	
	public Bootstrap(BookRepo bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}


	@Override
	public void run(String... args) throws Exception {
		Book dd = new Book("title", "12345");
		bookRepo.save(dd);
		Book noEJB = new Book("noEJB", "321654");
		bookRepo.save(noEJB);
		
		System.out.println("Start bootstrap");
		System.out.println("No of books = " + bookRepo.count());
	}

}
