package com.example.ticaretappv0.handling;

import com.example.ticaretappv0.model.enums.FileType;
import lombok.Data;

import java.io.File;

@Data
public class DataParserError {
    private String title;
    private FileType fileType;
    private String status;
}
