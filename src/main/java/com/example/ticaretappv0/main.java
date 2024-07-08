package com.example.ticaretappv0;

import com.example.ticaretappv0.anatation.ExcelColumn;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.services.ExcelService;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class main {
    public static void main(String[] args) {
        Category category = new Category();
        for(Field field : Category.class.getDeclaredFields()){
            System.out.println(field.getName());
        }
    }


}
