package com.example.ticaretappv0.controller;

import com.example.ticaretappv0.model.dto.request.RequestDto;
import com.example.ticaretappv0.model.enums.FileType;
import com.example.ticaretappv0.services.ExcelService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private ExcelService excelService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file,
                                             FileType fileType) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a valid Excel file.");
        }
        RequestDto requestDto = new RequestDto();
        requestDto.setFile(file);
        requestDto.setFileType(fileType);
        try {
            List<List<String>> data = excelService.readExcel(requestDto);
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("An error occurred while processing the Excel file.");
        }


    }
}