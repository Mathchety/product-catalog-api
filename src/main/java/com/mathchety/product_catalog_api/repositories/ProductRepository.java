package com.mathchety.product_catalog_api.repositories;

import com.mathchety.product_catalog_api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
