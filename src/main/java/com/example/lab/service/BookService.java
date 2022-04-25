package com.example.lab.service;

import com.example.lab.model.Book;
import com.example.lab.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> create(Book book);

    void delete(Long id);

    Optional<Book> edit(Long bookId, BookDto bookDto);

    Optional<Book> markAsTaken(Long id);

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> bookDtoToBook(BookDto book);
}
