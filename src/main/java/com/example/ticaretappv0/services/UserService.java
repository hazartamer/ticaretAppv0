package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.dto.UserExportDto;
import com.example.ticaretappv0.model.entity.Category;
import com.example.ticaretappv0.model.entity.DataUploadFail;
import com.example.ticaretappv0.model.entity.DataUploadFailedLines;
import com.example.ticaretappv0.model.entity.User;
import com.example.ticaretappv0.repository.CategoryRepository;
import com.example.ticaretappv0.repository.DataUploadFailedLinesRepository;
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
    private DataUploadFailedLinesService dataUploadFailedLinesService;


    @Override
    public void saveAll(List entities) {
        List<User> userList = new ArrayList<>();
        List<DataUploadFailedLines> failedLinesList = new ArrayList<>();
        DataUploadFail dataUploadFail = new DataUploadFail();

        for (Object obj : entities) {
            if (obj instanceof UserExportDto) { // DTO türünü kontrol et
                UserExportDto userDto = (UserExportDto) obj;
                User user = new User();

                if (userDto.isFailed()){
                    DataUploadFailedLines failedLines = DataUploadFailedLines.builder()
                            .line(userDto.getRowId())
                            .titleName(userDto.getDescription())
                            .build();
                    failedLinesList.add(failedLines);
                }
                user.setFirstName(userDto.getName());
                user.setEmail(userDto.getEmail());
                user.setAddress(userDto.getAddress());
                userList.add(user);
            }
        }
        dataUploadFail.setFileName("User Excel");
        dataUploadFail.setFailedLines(failedLinesList);
        dataUploadFailedLinesService.failedLinesSaver(failedLinesList,dataUploadFail);
        repository.saveAll(userList);
    }
}

