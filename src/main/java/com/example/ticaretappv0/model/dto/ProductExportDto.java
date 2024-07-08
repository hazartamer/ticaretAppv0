package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.Inventory;
import lombok.Data;


@Data
public class ProductExportDto {

    @ExcelColumn(name = "Name")
    private String name;

    @ExcelColumn(name = "UnitPrice")
    private String unitPrice;

    @ExcelColumn(name = "Category")
    private Category category;

    @ExcelColumn(name = "Inventory")
    private Inventory inventory;
}
