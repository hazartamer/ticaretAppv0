package com.example.ticaretappv0.model.entity;

import com.example.ticaretappv0.anatation.ExcelColumn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ExcelColumn(name = "Name")
    private String name;
    @ExcelColumn(name = "Age")
    private int age;
    @ExcelColumn(name = "Address")
    private String address;
    //separation of concerns
}
