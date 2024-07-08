package com.example.ticaretappv0.model.dto;

import com.example.ticaretappv0.anatation.ExcelColumn;
import lombok.Data;

@Data
public class UserExportDto {

    @ExcelColumn(name = "FirstName")
    private String firstName;
    @ExcelColumn(name = "LastName")
    private String lastName;
    @ExcelColumn(name = "Email")
    private String email;
    @ExcelColumn(name = "Password")
    private String password;
    @ExcelColumn(name = "Address")
    private String address;
    @ExcelColumn(name = "PhoneNumber")
    private String phoneNumber;
}
