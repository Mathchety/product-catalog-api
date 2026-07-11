package com.mathchety.product_catalog_api.controllers;

import com.mathchety.product_catalog_api.models.Category;
import com.mathchety.product_catalog_api.repositories.CategoryRepository;
import com.mathchety.product_catalog_api.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryRepository repository;

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category){
        if(repository.existsByNameIgnoreCase(category.getName())){
            throw new RuntimeException("A category with this name already exist");
        }
        Category newCategory = repository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {
        Page<Category> list = repository.findAll(pageable);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return ResponseEntity.ok(category);
    }
}
