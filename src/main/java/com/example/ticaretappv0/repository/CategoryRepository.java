package com.example.ticaretappv0.repository;

import com.example.ticaretappv0.model.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
