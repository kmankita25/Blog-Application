package com.blog.application.bog_app_api.services;

import com.blog.application.bog_app_api.payload.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    //create
     CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
     void deleteCategory(Integer categoryId);

    //get

    CategoryDto geteCategory(Integer categoryId);

    //get All
    List <CategoryDto> getCategories();


}
