package com.blog.application.bog_app_api.repositories;

import com.blog.application.bog_app_api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
