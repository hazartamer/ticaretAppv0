package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import lombok.Data;

@Data
public class UserExportDto extends AbstractExcelDto{

    @ExcelColumn(name = "Name" , required = true)
    private String name;
    @ExcelColumn(name = "Age", required = true)
    private int age;
    @ExcelColumn(name = "Email", required = true)
    private String email;
    @ExcelColumn(name = "Password", required = true)
    private String password;
    @ExcelColumn(name = "Address", required = true)
    private String address;
    @ExcelColumn(name = "PhoneNumber", required = true)
    private String phoneNumber;
}
