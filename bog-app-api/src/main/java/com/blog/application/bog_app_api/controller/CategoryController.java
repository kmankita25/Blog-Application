package com.blog.application.bog_app_api.controller;

import com.blog.application.bog_app_api.payload.ApiResponse;
import com.blog.application.bog_app_api.payload.CategoryDto;
import com.blog.application.bog_app_api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
       CategoryDto createCategory=this.categoryService.createCategory(categoryDto);
       return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){
        CategoryDto updateCategory=this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(updateCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
     this.categoryService.deleteCategory(categoryId);
     return new ResponseEntity<ApiResponse>(new ApiResponse("category delete successfully",true),HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId){
    CategoryDto categoryDto=this.categoryService.geteCategory(categoryId);
    return new ResponseEntity<CategoryDto>(categoryDto,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getCategories(){
    List<CategoryDto> categories =this.categoryService.getCategories();
    return  ResponseEntity.ok(categories);
    }
}