package com.example.ticaretappv0.services;

import com.example.ticaretappv0.handling.exceptions.DataParserException;
import com.example.ticaretappv0.model.dto.request.RequestDto;
import com.example.ticaretappv0.model.entity.DataUploadStatus;
import com.example.ticaretappv0.model.enums.DataUploadStatusEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CommonSetService <T> {
    private ExcelParser excelParser;
    private final ApplicationContext applicationContext;
    private ExcelStatusService excelStatusService;

    public List<?> organization(RequestDto requestDto) {
        try {
            DataUploadStatus dataUploadStatus = new DataUploadStatus();
            dataUploadStatus.setFileType(requestDto.getFileType());
            dataUploadStatus.setDataUploadStatusEnum(DataUploadStatusEnum.PENDING);
            // burayı fazla ayıkamadım nasıl yapılacağınıda böyleli bişi deeendik
            excelStatusService.statusSaver(dataUploadStatus);

            List<?> data = excelParser.readExcel(requestDto);
            TypeService<?> service = applicationContext.getBean(requestDto.getFileType().getServiceClass());
            service.saveAll(data);
            dataUploadStatus.setDataUploadStatusEnum(DataUploadStatusEnum.COMPLETED);
            excelStatusService.statusSaver(dataUploadStatus);
            return data;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DataParserException e) {
            throw new RuntimeException(e);
        }
    }
}
