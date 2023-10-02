package com.example.tpecommerce.controller;


import com.example.tpecommerce.model.enumeration.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@CrossOrigin("*")
public class CategoryController {


    @GetMapping()
    public ResponseEntity<List<String>> getAllCategories() {
        return ResponseEntity.ok(Arrays.stream(Category.values()).map(Category::name).toList());
    }
}
