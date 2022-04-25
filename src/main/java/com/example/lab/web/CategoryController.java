package com.example.lab.web;

import com.example.lab.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryController {
    @GetMapping
    public List<Category> getAllCategories(){
        System.out.println("CATEGORIESS");
        return Arrays.stream(Category.values()).collect(Collectors.toList());
    }
}
