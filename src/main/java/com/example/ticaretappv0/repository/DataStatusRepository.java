package com.example.ticaretappv0.repository;

import com.example.ticaretappv0.model.entity.DataUploadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataStatusRepository extends JpaRepository<DataUploadStatus , Long> {
}
