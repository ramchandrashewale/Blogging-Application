package com.blog.blog.controller;

import com.blog.blog.payloads.ApiResponse;
import com.blog.blog.payloads.CategoryDto;
import com.blog.blog.service.CategoryService;
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

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory= categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createCategory, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto updateCategory=categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(updateCategory,HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId){
        CategoryDto getCategory=categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(getCategory,HttpStatus.OK);
    }
    @GetMapping
    public  ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDto=categoryService.getAllCategory();
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("category is deleted successfully ", true),HttpStatus.OK);
    }
}
