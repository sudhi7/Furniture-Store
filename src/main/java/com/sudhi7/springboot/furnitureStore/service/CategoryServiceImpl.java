package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.dao.CategoryRepository;
import com.sudhi7.springboot.furnitureStore.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findCategoryById(int id) {
        Optional<Category> result = categoryRepository.findById(id);
        Category category = null;
        if(result.isPresent()) {
            category = result.get();
        }
        else {
            throw new RuntimeException("Did not find Category Id - " + id);
        }
        return category;
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }


}