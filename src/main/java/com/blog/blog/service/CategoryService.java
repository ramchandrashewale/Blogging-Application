package com.blog.blog.service;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.User;
import com.blog.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
     CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
     CategoryDto getCategoryById(Integer id);
     List<CategoryDto> getAllCategory();
    void deleteCategory(Integer categoryId);
}
