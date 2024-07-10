package com.example.ticaretappv0.model.enums;

import com.example.ticaretappv0.model.dto.CategoryExportDto;
import com.example.ticaretappv0.model.dto.InventoryExportDto;
import com.example.ticaretappv0.model.dto.ProductExportDto;
import com.example.ticaretappv0.model.dto.UserExportDto;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.services.*;

import java.lang.reflect.Type;

public enum FileType {
    CATEGORY(CategoryExportDto.class, CategoryService.class),
    INVENTORY(InventoryExportDto.class, InventoryService.class),
    PRODUCT(ProductExportDto.class, ProductService.class),
    USER(UserExportDto.class, UserService.class);

    private Class<?> excelModelClass;
    private Class<? extends TypeService<?>> serviceClass;

    FileType(Class<?> excelModelClass, Class<? extends TypeService<?>> serviceClass) {
        this.excelModelClass = excelModelClass;
        this.serviceClass = serviceClass;
    }

    public Class<?> getExcelModelClass() {
        return excelModelClass;
    }

    public Class<? extends TypeService<?>> getServiceClass() {
        return serviceClass;
    }
}
