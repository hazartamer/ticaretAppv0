package com.example.ticaretappv0.model.dto.request;

import com.example.ticaretappv0.model.enums.FileType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RequestDto {
    private FileType fileType;
    private MultipartFile file; // bilgisayara indirmiş olduğumuz dosyayı seçicez

}
