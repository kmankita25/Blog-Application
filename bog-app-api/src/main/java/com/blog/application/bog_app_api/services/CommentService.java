package com.blog.application.bog_app_api.services;

import com.blog.application.bog_app_api.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,Integer postId);
    void delete(Integer commentId);

}
