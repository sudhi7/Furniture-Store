package com.sudhi7.springboot.furnitureStore.controller;

import com.sudhi7.springboot.furnitureStore.entity.Category;
import com.sudhi7.springboot.furnitureStore.entity.Item;
import com.sudhi7.springboot.furnitureStore.service.CategoryService;
import com.sudhi7.springboot.furnitureStore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("admin/items/{itemId}")
    public String deleteItem(@PathVariable int itemId, Model model) {
        Item item = itemService.findItemById(itemId);
        if(item == null) {
            throw new RuntimeException("Item id not found - " + itemId);
        }
        itemService.deleteItem(itemId);
        return fetchAllItemsAndCategories(model);
    }

    @PostMapping("/admin/{categoryId}/submitItem")
    public String submitItem(@ModelAttribute("newItem") Item item, @PathVariable int categoryId, Model model) {
        item.setId(0);
        item.setCategory(categoryService.findCategoryById(categoryId));
        itemService.save(item);
        return fetchAllItemsAndCategories(model);
    }

    @PostMapping("/admin/{categoryId}/addItem")
    public String addItem(@PathVariable int categoryId, Model model) {
        Item item = new Item();
        model.addAttribute("newItem", item);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("categoryId", categoryId);
        return "item-form";
    }

    @GetMapping("/item/{itemId}")
    public String getItemDescription(@PathVariable int itemId, Model model) {
        Item item = itemService.findItemById(itemId);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("item", item);
        Category category = item.getCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("categoryId", category.getId());
        return "item-page";
    }

    @PostMapping("/item/{itemId}")
    public String itemDescription(@PathVariable int itemId, Model model) {
        return getItemDescription(itemId, model);
    }

    public String fetchAllItemsAndCategories(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        List<Item> itemList = itemService.findAll();
        model.addAttribute("items", itemList);
        model.addAttribute("categoryId", null);
        return "category-items";
    }

}