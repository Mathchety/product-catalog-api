package com.mathchety.product_catalog_api.controllers;

import com.mathchety.product_catalog_api.dto.ProductDTO;
import com.mathchety.product_catalog_api.models.Product;
import com.mathchety.product_catalog_api.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.lang.annotation.Repeatable;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
        Page<Product> list = service.findAll(pageable);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok(listDto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO dto) {
        Product product = new Product(null, dto.getName(), dto.getPrice(), dto.getQuantity());
        Product newProduct = service.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(newProduct));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id){
        Product product = service.findById(id);
        ProductDTO dto = new ProductDTO(product);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductDTO>> findByName(@RequestParam String name, Pageable pageable) {
        Page<Product> list = service.findByName(name, pageable);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok(listDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        Product product = new Product(id, dto.getName(), dto.getPrice(), dto.getQuantity());
        Product updatedProduct = service.update(id, product);
        return ResponseEntity.ok(new ProductDTO(updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
