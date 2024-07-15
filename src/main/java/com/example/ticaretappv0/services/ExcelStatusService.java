package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.entity.DataUploadStatus;
import com.example.ticaretappv0.repository.DataStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExcelStatusService {

    private DataStatusRepository repository;

    public void statusSaver(DataUploadStatus status) {
        repository.save(status);
    }
}
