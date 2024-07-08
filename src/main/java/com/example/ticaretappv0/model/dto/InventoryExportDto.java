package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import com.example.ticaretappv0.model.entity.Product;

public class InventoryExportDto {
    @ExcelColumn(name = "Quantity")
    private int quantity;

    @ExcelColumn(name = "Product") // ilişkili olduğu için ekstra bir şey yapmamız gerekebilir bakılacak!!
    private Product product;
}
