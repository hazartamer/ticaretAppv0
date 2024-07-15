package com.example.ticaretappv0.model.enums;


public enum DataUploadStatusEnum {
    PENDING("PENDİNG"),
    VALIDATION("VALIDATION"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED"),
    FAILED_ENUMS("FAILED_ENUMS");

    private String value;

    DataUploadStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
