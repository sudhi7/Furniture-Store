package com.sudhi7.springboot.furnitureStore.controller;

import com.sudhi7.springboot.furnitureStore.entity.Category;
import com.sudhi7.springboot.furnitureStore.entity.Item;
import com.sudhi7.springboot.furnitureStore.service.CategoryService;
import com.sudhi7.springboot.furnitureStore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/home")
    public String home(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<Item> itemList = itemService.findAll();
        model.addAttribute("items", itemList);
        model.addAttribute("categoryId", null);
        return "category-items";
    }

    @PostMapping("/home")
    public String getHome(Model model) {
        return home(model);
    }

}