package com.main.services;

import com.main.entity.Book;
import com.main.repository.BookRepository;
import com.main.dto.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    @CacheEvict(value = "books", allEntries = true)
    public Book save(BookRequest request) {
        var book = Book.builder()
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .build();
        return repository.save(book);
    }

    @Cacheable("books")
    public List<Book> findAll() {
        return repository.findAll();
    }
}
