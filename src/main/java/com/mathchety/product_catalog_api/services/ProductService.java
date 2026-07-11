package com.mathchety.product_catalog_api.services;

import com.mathchety.product_catalog_api.models.Category;
import com.mathchety.product_catalog_api.models.Product;
import com.mathchety.product_catalog_api.repositories.CategoryRepository;
import com.mathchety.product_catalog_api.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public Product create(Product product){
        if(product.getCategory() != null && product.getCategory().getId() != null){
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            product.setCategory(category);
        }
        if(repository.existsByNameIgnoreCase(product.getName())){
            throw new RuntimeException("A product with this name already exist");
        }
        return repository.save(product);
    }
    public Page<Product> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }
    public Product findById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public Page<Product> findByName(String name, Pageable pageable) {
        return repository.findByNameContainingIgnoreCase(name, pageable);
    }
    public Product update(Long id, Product product){
        Product produtoDoBanco = findById(id);
        product.setId(id);
        if(product.getCategory() != null && product.getCategory().getId() != null){
            Category category = categoryRepository.findById(product.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            product.setCategory(category);
        }
        return repository.save(product);
    }
    public void delete(Long id){
        Product product = findById(id);
        repository.delete(product);
    }

}
