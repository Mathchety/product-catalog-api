package com.mathchety.product_catalog_api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The product must have a name")
    private String name;

    @NotNull(message = "The product must have a price")
    @Positive(message = "The must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "The product must have a quantity number")
    @PositiveOrZero(message = "The quantity can't be negative")
    private int quantity;

}
