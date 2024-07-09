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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExcelService {
    final UserService service;
    final CategoryRepository repository;

    public List<List<String>> readExcel(RequestDto requestDto) throws IOException {
        List<List<String>> data = new ArrayList<>();
        List<Object> insntaceList = new ArrayList<>();


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
                Class<?> clazz = requestDto.getFileType().getExcelModelClass();
                Object instance = getInstance(clazz);

                populateCategoryFromRow(columnIndexes, row, instance);
                insntaceList.add(instance);
            }
        }
        service.deneme(insntaceList);
        return data;
    }


    private void populateCategoryFromRow(Map<String, Integer> columnIndexes, Row row, Object instance) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(ExcelColumn.class)) {
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                String columnName = annotation.name(); //Name
                if (columnIndexes.containsKey(columnName)) {
                    int colIndex = columnIndexes.get(columnName);
                    Cell cell = row.getCell(colIndex);
                    field.setAccessible(true);
                    try {
                        setFieldValueFromCell(instance, field, cell);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace(); // Hata durumunda hata ayıklama çıktısını yazdır
                    }
                }
            }
        }
    }

    private void setFieldValueFromCell(Object instance, Field field, Cell cell) throws IllegalAccessException {
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    if (field.getType().equals(String.class)) {
                        field.set(instance, cell.getStringCellValue());
                    }
                    break;
                case NUMERIC:
                    if (field.getType().equals(Integer.class) || field.getType().equals(int.class)) {
                        field.set(instance, (int) cell.getNumericCellValue());
                    } else if (field.getType().equals(Double.class)) {
                        field.set(instance, cell.getNumericCellValue());
                    }
                    break;
                default:
                    field.set(instance, cell.toString());
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

    private <T>  T getInstance(Class<T> clazz){
        T tObject;
        try{
            Constructor<T> declaredConstructor = clazz.getDeclaredConstructor();

            if(!(declaredConstructor.canAccess(null))){
                declaredConstructor.setAccessible(true);
            }
            tObject = declaredConstructor.newInstance();

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return tObject;
    }

}
