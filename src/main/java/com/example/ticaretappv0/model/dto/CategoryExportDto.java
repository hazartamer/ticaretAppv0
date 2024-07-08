package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import lombok.Data;

@Data
public class CategoryExportDto {
    @ExcelColumn(name = "Name")
    private String name;
    @ExcelColumn(name = "Age")
    private int age;
    @ExcelColumn(name = "Address")
    private String address;
}
