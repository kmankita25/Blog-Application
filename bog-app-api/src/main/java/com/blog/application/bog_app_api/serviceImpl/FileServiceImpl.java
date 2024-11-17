package com.blog.application.bog_app_api.serviceImpl;

import com.blog.application.bog_app_api.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws Exception {
        //file name
       String filename= file.getOriginalFilename();
        //random name genrate
       String randomName= UUID.randomUUID().toString();
       String name=randomName+filename.substring(filename.lastIndexOf(".png"));
        //full path
       String filePath= path+ File.separator+name;
        //created folder if not exist
        File f=new File(path);
        if(!f.exists())
            f.mkdir();
        //file copy
        Files.copy( file.getInputStream(),Paths.get(filePath));
        return name;
    }

    @Override
    public InputStream getResources(String path, MultipartFile file) throws Exception {
        String fullPath=path+File.separator+file;
        InputStream inputStream=new FileInputStream(fullPath);
        return inputStream;
    }
}
