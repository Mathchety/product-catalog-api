package com.mathchety.product_catalog_api.services;

import com.mathchety.product_catalog_api.models.Product;
import com.mathchety.product_catalog_api.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product create(Product product){
        return repository.save(product);
    }
    public List<Product> findAll(){
        return repository.findAll();
    }
    public Product findById(Long id){
        return repository.findById(id).orElseThrow();
    }
    public Product update(Long id, Product product){
        Product produtoDoBanco = findById(id);
        product.setId(id);
        return repository.save(product);
    }
    public void delete(Long id){
        Product product = findById(id);
        repository.delete(product);
    }

}
