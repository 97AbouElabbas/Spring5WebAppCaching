package com.example.demo.exceptions;

public class BookNotFoundException extends RuntimeException{
	public BookNotFoundException(int id){
		super("Could not find employee " + id);
	}
}