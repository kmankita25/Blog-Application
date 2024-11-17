package com.blog.application.bog_app_api.serviceImpl;

import com.blog.application.bog_app_api.entity.Category;
import com.blog.application.bog_app_api.exceptions.ResourceNotFoundException;
import com.blog.application.bog_app_api.payload.CategoryDto;
import com.blog.application.bog_app_api.repositories.CategoryRepo;
import com.blog.application.bog_app_api.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=this.modelMapper.map(categoryDto, Category.class);
        Category category1=this.categoryRepo.save(category);
        return this.modelMapper.map(category1,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Category Id",categoryId));
        category.setCategoryTitle(category.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());
        Category updatedCategory=this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        this.categoryRepo.delete(category);
    }

    @Override
    public CategoryDto geteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryId",categoryId));
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
      List<Category>categories=this.categoryRepo.findAll();
      List<CategoryDto>categoryDtos=categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
