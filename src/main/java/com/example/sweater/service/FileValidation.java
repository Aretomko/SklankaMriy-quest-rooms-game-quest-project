package com.example.sweater.service;

import org.springframework.stereotype.Service;

@Service
public class FileValidation {
    public boolean isVideo(String filename){
        return filename.endsWith(".mp4");
    }
    public boolean isImage(String filename){
        return filename.endsWith(".jpg") || filename.endsWith(".png") || filename.endsWith(".gif") || filename.endsWith(".bmp");
    }
}
