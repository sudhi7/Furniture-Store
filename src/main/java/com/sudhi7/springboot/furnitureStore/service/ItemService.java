package com.sudhi7.springboot.furnitureStore.service;



import com.sudhi7.springboot.furnitureStore.entity.Item;

import java.util.List;

public interface ItemService {

    public List<Item> findAll();

    public Item findItemById(int id);

    public void deleteItem(int id);

    public void save(Item item);
}
