package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.dto.CategoryExportDto;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService implements TypeService<CategoryExportDto> {
    private CategoryRepository repository;
    @Override
    public void saveAll(List entities) {
        List<Category> userList = new ArrayList<>();
        for (Object obj : entities) {
            if (obj instanceof CategoryExportDto) {
                CategoryExportDto categoryExportDto = (CategoryExportDto) obj;
                Category category = new Category();
                category.setName(categoryExportDto.getName());
            }
        }
        repository.saveAll(userList);
    }
}
