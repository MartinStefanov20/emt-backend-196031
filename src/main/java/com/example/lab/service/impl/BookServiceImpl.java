package com.example.lab.service.impl;

import com.example.lab.model.Author;
import com.example.lab.model.Book;
import com.example.lab.model.dto.BookDto;
import com.example.lab.model.enumerations.Category;
import com.example.lab.model.exceptions.AuthorNotFoundException;
import com.example.lab.model.exceptions.BookNotFoundException;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.BookRepository;
import com.example.lab.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public Optional<Book> create(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        System.out.println(bookDto.getCategory());
        System.out.println(Category.valueOf(bookDto.getCategory()));
        Category c = Category.valueOf(bookDto.getCategory());
        book.setCategory(c);

        Author a = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(a);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        book.setAvailableCopies(book.getAvailableCopies()-1);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name){
        return bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> bookDtoToBook(BookDto book) {
        Author author = authorRepository.findById(book.getAuthor()).orElseThrow(() -> new AuthorNotFoundException(book.getAuthor()));
        Category category = Category.valueOf(book.getCategory());
        Book b = null;
        if(book.getName()!=null && book.getAvailableCopies()!=null )
            b = new Book(book.getName(), author, category, book.getAvailableCopies());
        return Optional.of(b);
    }
}
