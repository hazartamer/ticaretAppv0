package com.example.ticaretappv0.model.enums;

import com.example.ticaretappv0.model.dto.CategoryExportDto;
import com.example.ticaretappv0.model.dto.InventoryExportDto;
import com.example.ticaretappv0.model.dto.ProductExportDto;
import com.example.ticaretappv0.model.dto.UserExportDto;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.Inventory;
import com.example.ticaretappv0.model.entity.Product;
import com.example.ticaretappv0.model.entity.User;

public enum FileType {
    CATEGORY(CategoryExportDto.class),//modelkategori
    INVENTORY(InventoryExportDto.class),
    PRODUCT(ProductExportDto.class),
    USER(UserExportDto.class);

    private Class<?> excelModelClass;

    public Class<?> getExcelModelClass() {
        return excelModelClass;
    }

    FileType(Class<?> excelModelClass) {
        this.excelModelClass = excelModelClass;
    }
}
