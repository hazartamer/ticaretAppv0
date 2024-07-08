package com.example.ticaretappv0.services;


import com.example.ticaretappv0.anatation.ExcelColumn;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExcelService {
    final CategoryRepository repository;

    public List<List<String>> readExcel(MultipartFile file) throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);

            // Başlık satırındaki sütun isimlerini ve indekslerini haritaya ekle
            Map<String, Integer> columnIndexes = getTitleMap(sheet);

            // Her satırı oku ve veritabanına kaydet
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // Başlık satırını atla
                Row row = sheet.getRow(rowIndex);
                List<String> rowData = new ArrayList<>();
                for (int colIndex = 0; colIndex < row.getPhysicalNumberOfCells(); colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    System.out.println(cell);
                    rowData.add(cell.toString());
                }
                data.add(rowData);


                // create instance from class type reflection
                Category excelData = new Category();
                for (Field field : Category.class.getDeclaredFields()) {
                    if (field.isAnnotationPresent(ExcelColumn.class)) {
                        ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                        String columnName = annotation.name(); //Name
                        if (columnIndexes.containsKey(columnName)) {
                            int colIndex = columnIndexes.get(columnName);
                            Cell cell = row.getCell(colIndex);
                            field.setAccessible(true);
                            try {
                                if (cell != null) {
                                    switch (cell.getCellType()) {
                                        case STRING:
                                            if (field.getType().equals(String.class)) {
                                                field.set(excelData, cell.getStringCellValue());
                                            }
                                            break;
                                        case NUMERIC:
                                            if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                                                field.set(excelData, (int) cell.getNumericCellValue());
                                            } else if (field.getType().equals(Double.class)) {
                                                field.set(excelData, cell.getNumericCellValue());
                                            }
                                            break;
                                        default:
                                            field.set(excelData, cell.toString());
                                            break;
                                    }
                                }
                            } catch (IllegalAccessException e) {
                                e.printStackTrace(); // Hata durumunda hata ayıklama çıktısını yazdır
                            }
                        }
                    }
                }
                repository.save(excelData);
            }
        }
        return data;
        }

    private Map<String, Integer> getTitleMap(Sheet sheet) {
        Map<String, Integer> columnIndexes = new HashMap<>();
        Row headerRow = sheet.getRow(0);
        for (int colIndex = 0; colIndex < headerRow.getPhysicalNumberOfCells(); colIndex++) {
            Cell cell = headerRow.getCell(colIndex);
            columnIndexes.put(cell.getStringCellValue(), colIndex);
        }
        return columnIndexes;
    }
}
