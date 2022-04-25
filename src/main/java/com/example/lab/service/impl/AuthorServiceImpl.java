package com.example.lab.service.impl;

import com.example.lab.model.Author;
import com.example.lab.model.Country;
import com.example.lab.model.dto.AuthorDto;
import com.example.lab.model.exceptions.AuthorNotFoundException;
import com.example.lab.repository.AuthorRepository;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public Optional<Author> create(Author a) {
        return Optional.of(authorRepository.save(a));
    }

    @Override
    public void delete(Long id) {

        Optional<Author> a = authorRepository.findById(id);
        if(a.isPresent())
            authorRepository.delete(a.get());
        else throw new AuthorNotFoundException(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> authorDtoToAuthor(AuthorDto author) {
        Country country = countryRepository.findById(author.getCountryId()).orElseThrow(()->new EmptyStackException());

        Author b = null;

        if (author.getName() != null && author.getSurname()!= null)
            b = new Author(author.getName(),author.getSurname(),country);
        return Optional.of(b);
    }
}
