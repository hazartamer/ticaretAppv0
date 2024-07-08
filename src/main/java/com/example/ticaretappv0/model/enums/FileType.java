package com.example.ticaretappv0.model.enums;

import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.Inventory;
import com.example.ticaretappv0.model.entity.Product;
import com.example.ticaretappv0.model.entity.User;

public enum FileType {
    CATEGORY(Category.class),//modelkategori
    INVENTORY(Inventory.class),
    PRODUCT(Product.class),
    USER(User.class);
    private final Class<?> excelModelClass;

    FileType(Class<?> excelModelClass) {
        this.excelModelClass = excelModelClass;
    }
}
