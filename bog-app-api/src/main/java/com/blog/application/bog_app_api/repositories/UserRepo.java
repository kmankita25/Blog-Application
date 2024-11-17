package com.blog.application.bog_app_api.repositories;

import com.blog.application.bog_app_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
