package com.example.ticaretappv0.repository;

import com.example.ticaretappv0.model.entity.SavedFileInformation;
import jakarta.websocket.server.PathParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SavedFileInformationRepository extends JpaRepository<SavedFileInformation,Long> {
    @Query(value = "SELECT saved_file_information.file_name FROM saved_file_information WHERE saved_file_information.file_type = :fileType" , nativeQuery = true)
    String getUrlByFileName(@PathParam(value = "fileType") String fileType);

}
