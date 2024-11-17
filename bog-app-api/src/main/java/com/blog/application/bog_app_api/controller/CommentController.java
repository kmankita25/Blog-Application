package com.blog.application.bog_app_api.controller;

import com.blog.application.bog_app_api.entity.Comment;
import com.blog.application.bog_app_api.payload.ApiResponse;
import com.blog.application.bog_app_api.payload.CommentDto;
import com.blog.application.bog_app_api.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Integer postId){
        CommentDto comment=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
        this.commentService.delete(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);

    }
}
