package com.blog.application.bog_app_api.controller;

import com.blog.application.bog_app_api.entity.Post;
import com.blog.application.bog_app_api.payload.FileResponse;
import com.blog.application.bog_app_api.payload.PostDto;
import com.blog.application.bog_app_api.services.FileService;
import com.blog.application.bog_app_api.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.io.OutputStream;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private PostService postService;
    @Value("${project.image}")
    private String path;
    @PostMapping("/upload/{postId}")
    public ResponseEntity<PostDto>fileUpload(@RequestParam("image")MultipartFile image,@PathVariable("postId") Integer postId) throws Exception {
        String fileName=null;
            PostDto  postById =this.postService.getPostById(postId);
            fileName=this.fileService.uploadImage(path,image);
            postById.setImageName(fileName);
            PostDto postDto=this.postService.updatePost(postById,postId);

       /* }*/
       /* catch (Exception exception){
            exception.printStackTrace();
           *//* return new ResponseEntity<>(new FileResponse(null,"image is not uploades due to server error"), HttpStatus.INTERNAL_SERVER_ERROR);*//*
        }*/
   /* return new ResponseEntity<>(new FileResponse(fileName,"image successfully uploaded"), HttpStatus.OK);*/
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    //method to download file
    @GetMapping(value = "/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public  void downloadFile(@PathVariable("imageName") MultipartFile imageName, HttpServletResponse response) throws Exception {
       InputStream resources = this.fileService.getResources(path,imageName);
        StreamUtils.copy(resources, OutputStream.nullOutputStream());
    }
}
