package com.example.ticaretappv0.repository;

import com.example.ticaretappv0.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
