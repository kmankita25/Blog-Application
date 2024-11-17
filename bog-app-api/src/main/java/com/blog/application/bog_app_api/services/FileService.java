package com.blog.application.bog_app_api.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
    String uploadImage(String path, MultipartFile file) throws Exception;
    InputStream getResources(String path,MultipartFile file) throws Exception;
}
