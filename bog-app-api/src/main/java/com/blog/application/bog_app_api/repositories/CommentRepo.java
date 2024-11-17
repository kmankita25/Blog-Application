package com.blog.application.bog_app_api.repositories;

import com.blog.application.bog_app_api.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
