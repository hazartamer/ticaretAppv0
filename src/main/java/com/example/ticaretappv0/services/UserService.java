package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.dto.UserExportDto;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.User;
import com.example.ticaretappv0.repository.CategoryRepository;
import com.example.ticaretappv0.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService implements TypeService<UserExportDto> {
    private UserRepository repository;


    @Override
    public void saveAll(List entities) {
        List<User> userList = new ArrayList<>();
        for (Object obj : entities) {
            if (obj instanceof UserExportDto) { // DTO türünü kontrol et
                UserExportDto userDto = (UserExportDto) obj;
                User user = new User();
                user.setFirstName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setAddress(userDto.getAddress());
                userList.add(user);
            }
        }
        repository.saveAll(userList);
    }
}

