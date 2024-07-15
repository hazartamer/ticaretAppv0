package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.Inventory;
import lombok.Data;


@Data
public class ProductExportDto extends AbstractExcelDto{

    @ExcelColumn(name = "Name", required = true)
    private String name;

    @ExcelColumn(name = "UnitPrice", required = true)
    private String unitPrice;

    @ExcelColumn(name = "Category", required = true)
    private Category category;

    @ExcelColumn(name = "Inventory", required = true)
    private Inventory inventory;
}
