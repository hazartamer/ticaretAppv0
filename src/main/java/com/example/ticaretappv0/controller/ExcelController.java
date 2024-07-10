package com.example.ticaretappv0.controller;

import com.example.ticaretappv0.model.dto.request.RequestDto;
import com.example.ticaretappv0.model.enums.FileType;
import com.example.ticaretappv0.services.ExcelService;
import com.example.ticaretappv0.services.TypeService;
import com.example.ticaretappv0.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.poi.sl.usermodel.TextRun;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private ExcelService excelService;
    private final ApplicationContext applicationContext;

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
            List<T> data = excelService.readExcel(requestDto);
            TypeService<?> service = (TypeService<?>) applicationContext.getBean(fileType.getServiceClass());
            service.saveAll(data);
            return ResponseEntity.ok(data);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("An error occurred while processing the Excel file.");
        }


    }
}