package com.example.ticaretappv0.controller;


import com.example.ticaretappv0.model.dto.request.RequestDto;
import com.example.ticaretappv0.model.enums.FileType;
import com.example.ticaretappv0.services.CommonSetService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/excel")
public class ExcelController {
    private CommonSetService commonSetService;

    public ExcelController(CommonSetService commonSetService) {
        this.commonSetService = commonSetService;
    }

    private final String uploadDir = "/Users/salih/Desktop";


    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file,
                                             FileType fileType , RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }
        RequestDto requestDto = new RequestDto();
        requestDto.setFile(file);
        requestDto.setFileType(fileType);


        Path path = Paths.get(uploadDir, file.getOriginalFilename());

        // Dosyayı belirli bir dizine kaydet
        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return ResponseEntity.ok(commonSetService.organization(requestDto));
    }
}