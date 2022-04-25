package com.example.lab.model;

import com.example.lab.model.enumerations.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    @OneToOne
    private Author author;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    private Integer availableCopies;

    public Book(String name, Author author, Category category, Integer availableCopies) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.availableCopies = availableCopies;
    }
}
