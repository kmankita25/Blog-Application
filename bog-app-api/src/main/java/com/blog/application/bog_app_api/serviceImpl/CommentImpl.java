package com.blog.application.bog_app_api.serviceImpl;

import com.blog.application.bog_app_api.entity.Comment;
import com.blog.application.bog_app_api.entity.Post;
import com.blog.application.bog_app_api.exceptions.ResourceNotFoundException;
import com.blog.application.bog_app_api.payload.CommentDto;
import com.blog.application.bog_app_api.repositories.CommentRepo;
import com.blog.application.bog_app_api.repositories.PostRepo;
import com.blog.application.bog_app_api.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CommentImpl implements CommentService {
    @Autowired

    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post Id",postId));
        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void delete(Integer commentId) {

        Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
        this.commentRepo.delete(comment);

    }
}
