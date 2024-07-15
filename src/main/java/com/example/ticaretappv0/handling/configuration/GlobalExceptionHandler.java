package com.example.ticaretappv0.handling.configuration;

import com.example.ticaretappv0.handling.DataParserError;
import com.example.ticaretappv0.handling.exceptions.DataParserException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DataParserException.class)
    public DataParserError dataParserExceptionHandler(){
        DataParserError dataParserError = new DataParserError();
        dataParserError.setTitle("name file is missing");
        return dataParserError;
    }
}
