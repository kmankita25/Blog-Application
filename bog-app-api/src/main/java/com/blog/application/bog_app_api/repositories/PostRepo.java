package com.blog.application.bog_app_api.repositories;

import com.blog.application.bog_app_api.entity.Category;
import com.blog.application.bog_app_api.entity.Post;
import com.blog.application.bog_app_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    List<Post> findByTitleContaining(String title);
}
