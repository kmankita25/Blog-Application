package com.blog.application.bog_app_api.controller;

import com.blog.application.bog_app_api.config.AppConstant;
import com.blog.application.bog_app_api.entity.Post;
import com.blog.application.bog_app_api.payload.ApiResponse;
import com.blog.application.bog_app_api.payload.PostDto;
import com.blog.application.bog_app_api.payload.PostResponse;
import com.blog.application.bog_app_api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/userId/{userId}/categoryId/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){
        PostDto createPostDto =this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPostDto, HttpStatus.CREATED);
    }

    //getbyuser
    @GetMapping("user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getpostByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList=this.postService.getPostsByUser(userId);
        return new ResponseEntity <List<PostDto>>(postDtoList,HttpStatus.OK);
    }

    //getByCategory

    @GetMapping("category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getpostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity <List<PostDto>>(postDtoList,HttpStatus.OK);
    }

    //get all post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse>getAllPost(@RequestParam (value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber
            , @RequestParam (value = "pageSize",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageSize,
                                                  @RequestParam (value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false) String sortBy,
                                                  @RequestParam (value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false) String sortDir){
        PostResponse postDtoList=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postDtoList,HttpStatus.OK);
    }

    //get single post
    @GetMapping("/post/{postId}")
    public ResponseEntity <PostDto> getSinglePost(@PathVariable Integer postId){

        PostDto postDto=this.postService.getPostById(postId);

        return new ResponseEntity <PostDto>(postDto,HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("post/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        this.postService.deletePost(postId);
        return new ApiResponse("delete sucessfully",true);
    }

    //update post
@PutMapping("post/{postId}")
    public ResponseEntity updatePost(@RequestBody PostDto postDto,Integer postId)
    {
        PostDto updatedPostDto=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDto>>searchPosts(@PathVariable String keyword){
        List<PostDto> postDtos=this.postService.searchPosts(keyword);
        return new ResponseEntity <List<PostDto>>(postDtos,HttpStatus.OK);

    }

}
