package com.example.ticaretappv0.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DataUploadFail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String FileName;

    @OneToMany(mappedBy = "dataUploadFail" , fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<DataUploadFailedLines> failedLines;
}
