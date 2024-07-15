package com.example.ticaretappv0.model.entity;

import com.example.ticaretappv0.model.enums.DataUploadStatusEnum;
import com.example.ticaretappv0.model.enums.FileType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataUploadStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private FileType fileType;

    @Enumerated(value = EnumType.STRING)
    private DataUploadStatusEnum dataUploadStatusEnum;
}
