package com.blog.application.bog_app_api.services;

import com.blog.application.bog_app_api.entity.Post;
import com.blog.application.bog_app_api.payload.PostDto;
import com.blog.application.bog_app_api.payload.PostResponse;

import java.util.List;

public interface PostService {

    //create

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    ////19 video
    //update
    PostDto updatePost(PostDto postDto,Integer postId);
    //delete
    void deletePost(Integer postId);
    //get all post
     PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    //get single post

    PostDto getPostById(Integer postId);
    //get all posts by catogory

    List<PostDto> getPostByCategory(Integer categoryId);
    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);
    //search post
    List<PostDto> searchPosts(String keyword);

}
