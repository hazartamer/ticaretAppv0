package com.example.ticaretappv0.repository;

import com.example.ticaretappv0.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
