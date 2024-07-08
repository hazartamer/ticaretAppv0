package com.example.ticaretappv0.repository;

import com.example.ticaretappv0.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
