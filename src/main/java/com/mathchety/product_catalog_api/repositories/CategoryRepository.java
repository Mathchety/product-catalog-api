package com.mathchety.product_catalog_api.repositories;

import com.mathchety.product_catalog_api.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByNameIgnoreCase(String name);
}
