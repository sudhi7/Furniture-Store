package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public void deleteCategory(int id);
    
    public Category findCategoryById(int id);

    public void save(Category category);
}
