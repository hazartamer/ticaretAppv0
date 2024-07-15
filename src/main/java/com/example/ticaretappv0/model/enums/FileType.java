package com.example.ticaretappv0.model.enums;

import com.example.ticaretappv0.model.dto.*;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.Inventory;
import com.example.ticaretappv0.model.entity.Product;
import com.example.ticaretappv0.model.entity.User;
import com.example.ticaretappv0.services.*;

public enum FileType {
    CATEGORY(CategoryExportDto.class, CategoryService.class, Category.class ),
    INVENTORY(InventoryExportDto.class, InventoryService.class, Inventory.class ),
    PRODUCT(ProductExportDto.class, ProductService.class, Product.class ),
    USER(UserExportDto.class, UserService.class, User.class );

    private Class<?> excelModelClass;
    private Class<? extends TypeService<?>> serviceClass;
    private Class<?> directClass;



    FileType(Class<? extends AbstractExcelDto> excelModelClass, Class<? extends TypeService<?>> serviceClass, Class<?> directClass) {
        this.excelModelClass = excelModelClass;
        this.serviceClass = serviceClass;
        this.directClass = directClass;
    }

    public Class<?> getDirectClass() {
        return directClass;
    }


    public Class<?> getExcelModelClass() {
        return excelModelClass;
    }

    public Class<? extends TypeService<?>> getServiceClass() {
        return serviceClass;
    }
}
