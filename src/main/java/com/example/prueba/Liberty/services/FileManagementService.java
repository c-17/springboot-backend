package com.example.prueba.Liberty.services;

import com.example.prueba.Liberty.interfaces.FileManagementInterface;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileManagementService implements FileManagementInterface, ServletContextAware {
    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public String uploadFile(MultipartFile file) throws IllegalStateException, IOException {
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());

        file.transferTo(new File(this.servletContext.getRealPath("/images/") + fileName));

        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/images/" + fileName;
    }

    @Override
    public boolean deleteFile(String fileName) throws IllegalStateException, IOException {
        return new File(this.servletContext.getRealPath("/images/") + fileName).delete();
    }
}
