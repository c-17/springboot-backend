package com.example.prueba.Liberty.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileManagementInterface {
    public String uploadFile(MultipartFile file) throws IllegalStateException, IOException;

    public boolean deleteFile(String fileName) throws IllegalStateException, IOException;
}
