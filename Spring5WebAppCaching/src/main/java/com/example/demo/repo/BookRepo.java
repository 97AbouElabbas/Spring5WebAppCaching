package com.example.demo.repo;

import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
	@Override
	@Cacheable(value = "books")
	Optional<Book> findById(Integer id);
	
}
