package com.example.ticaretappv0.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {
    public List<List<String>> readExcel(MultipartFile file) throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0); // İlk sayfayı (sheet) seç
            Map<String, Integer> headers = new HashMap<>();

            Row headerRow = sheet.getRow(0);
            for (int colIndex = 0; colIndex < headerRow.getPhysicalNumberOfCells(); colIndex++) {
                headers.put(headerRow.getCell(colIndex).getStringCellValue(), colIndex);
            }
            System.out.println(headerRow);

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    rowData.add(cell.toString());
                }
                data.add(rowData);
            }
        }

        return data;
    }
}
