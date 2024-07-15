package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.dto.request.RequestDto;
import com.example.ticaretappv0.model.entity.SavedFileInformation;
import com.example.ticaretappv0.repository.SavedFileInformationRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service

public class SavedFileInformationService {
    private SavedFileInformationRepository repository;

    public SavedFileInformationService(SavedFileInformationRepository repository) {
        this.repository = repository;
    }

    private final String uploadDir = "/Users/salih/Desktop";

    private int x = 0;

    public Path FileInformationSaver(RequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);
        x++;

        Path savedPath = Paths.get(uploadDir,  formattedDateTime +requestDto.getFile().getOriginalFilename());

        // Dosyayı belirli bir dizine kaydet
        try {
            Files.copy(requestDto.getFile().getInputStream(), savedPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SavedFileInformation savedFileInformation = new SavedFileInformation();
        savedFileInformation.setFileName(formattedDateTime + requestDto.getFile().getOriginalFilename());
        savedFileInformation.setFileType(requestDto.getFileType().toString());

        repository.save(savedFileInformation);

        String fileUrl = "/" ;
        fileUrl += repository.getUrlByFileName(requestDto.getFileType().toString());

        Path path = Paths.get(uploadDir + fileUrl);

        return path;
    }
}
