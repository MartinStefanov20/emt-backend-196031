package com.example.lab.service;

import com.example.lab.model.Author;
import com.example.lab.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> create(Author a);

    void delete(Long id);

    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> authorDtoToAuthor(AuthorDto author);

}
