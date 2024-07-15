package com.example.ticaretappv0.anatation;

import jakarta.annotation.Resource;
import jakarta.annotation.Resources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    String name();
    boolean required();
}
