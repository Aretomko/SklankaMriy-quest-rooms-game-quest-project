package com.example.sweater.service.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {
    @Value("${upload.path}")
    private String uploadPath;

    public String fileService(MultipartFile file) throws IOException {
        String resultFileName = null;
        if (file !=null && !file.getOriginalFilename().equals("")){
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            if (file.getOriginalFilename() != null) resultFileName = uuidFile + "." + file.getOriginalFilename();
            else resultFileName = null;
            file.transferTo(new File(uploadPath + "/" + resultFileName));
        }
        return resultFileName;
    }
}