package com.example.ticaretappv0.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DataUploadFailedLines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int line;

    private String titleName;

    @ManyToOne
    @JoinColumn(name = "data_upload_fail")
    private DataUploadFail dataUploadFail;
}
