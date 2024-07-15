package com.example.ticaretappv0.services;

import com.example.ticaretappv0.model.entity.DataUploadFail;
import com.example.ticaretappv0.model.entity.DataUploadFailedLines;
import com.example.ticaretappv0.repository.DataUploadFailRepository;
import com.example.ticaretappv0.repository.DataUploadFailedLinesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DataUploadFailedLinesService {

    private DataUploadFailedLinesRepository dataUploadFailedLinesRepository;
    private DataUploadFailRepository dataUploadFailRepository;


    public void failedLinesSaver(List<DataUploadFailedLines> failedLinesList , DataUploadFail dataUploadFail) {
        dataUploadFailedLinesRepository.saveAll(failedLinesList);
        dataUploadFail.setFailedLines(failedLinesList);

        dataUploadFailRepository.save(dataUploadFail);
    }
}
