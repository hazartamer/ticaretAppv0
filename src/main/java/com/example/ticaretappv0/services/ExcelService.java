package com.example.ticaretappv0.services;


import com.example.ticaretappv0.anatation.ExcelColumn;
import com.example.ticaretappv0.model.dto.CategoryExportDto;
import com.example.ticaretappv0.model.dto.InventoryExportDto;
import com.example.ticaretappv0.model.dto.ProductExportDto;
import com.example.ticaretappv0.model.dto.UserExportDto;
import com.example.ticaretappv0.model.dto.request.RequestDto;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.enums.FileType;
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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExcelService {
    final CategoryRepository repository;

    public List<List<String>> readExcel(RequestDto requestDto) throws IOException {
        List<List<String>> data = new ArrayList<>();
        requestDto.setFileType(FileType.CATEGORY);

        try (InputStream is = requestDto.getFile().getInputStream()) {
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
                Class<?> clazz = getReflectionClass(requestDto.getFileType());
                clazz.getName();
                Category excelData = new Category();
                populateCategoryFromRow(columnIndexes, row, clazz);
                repository.save(excelData);
            }
        }
        return data;
    }

    private Class<?> getReflectionClass(FileType fileType) {
        try {
            Class<?> clazz = fileType.getExcelModelClass();
            if (clazz == CategoryExportDto.class) {
                return Class.forName("com.example.ticaretappv0.model.dto.CategoryExportDto");
            } else if (clazz == ProductExportDto.class) {
                return Class.forName("com.example.ProductExportDto");
            } else if (clazz == InventoryExportDto.class) {
                return Class.forName("com.example.InventoryExportDto");
            } else if (clazz == UserExportDto.class) {
                return Class.forName("com.example.UserExportDto");
            } else {
                throw new IllegalArgumentException("Unsupported FileType: " + fileType.getExcelModelClass().getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating instance of class", e);
        }
    }

    private void populateCategoryFromRow(Map<String, Integer> columnIndexes, Row row, Class<?> excelData) {
        for (Field field : Category.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                String columnName = annotation.name(); //Name
                if (columnIndexes.containsKey(columnName)) {
                    int colIndex = columnIndexes.get(columnName);
                    Cell cell = row.getCell(colIndex);
                    field.setAccessible(true);
                    try {
                        setFieldValueFromCell(excelData, field, cell);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace(); // Hata durumunda hata ayıklama çıktısını yazdır
                    }
                }
            }
        }
    }

    private void setFieldValueFromCell(Class<?> excelData, Field field, Cell cell) throws IllegalAccessException {
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
