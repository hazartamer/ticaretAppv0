package com.example.ticaretappv0.model.dto;

import lombok.Data;

@Data
public abstract class AbstractExcelDto {
    private int rowId;
    private boolean isFailed;
    private String description;
}
