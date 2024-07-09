package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.User;
import com.example.ticaretappv0.repository.CategoryRepository;
import com.example.ticaretappv0.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private CategoryRepository repository;


    public void deneme(List<Object> instanceList) {
        List<Category> userList = new ArrayList<>();
        Category category = new Category();

        List<Object> objects = new ArrayList<>();

        for (Object obj : instanceList) {
            Class<?> clazz = obj.getClass();

            // Nesnenin tüm alanlarını al
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    // Alanın değerini al ve yazdır
                    Object value = field.get(obj);
                    System.out.println(field.getName() + " = " + value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private static void printFieldValues(Object obj) {
        // Nesnenin sınıfını al
        Class<?> clazz = obj.getClass();

        // Nesnenin tüm alanlarını al
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                // Alanın değerini al ve yazdır
                Object value = field.get(obj);
                System.out.println(field.getName() + " = " + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}

