package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import lombok.Data;

@Data
public class CategoryExportDto extends AbstractExcelDto{
    @ExcelColumn(name = "Name" , required = true)
    private String name;
    @ExcelColumn(name = "Age" , required = true)
    private int age;
    @ExcelColumn(name = "Address", required = true)
    private String address;
}
