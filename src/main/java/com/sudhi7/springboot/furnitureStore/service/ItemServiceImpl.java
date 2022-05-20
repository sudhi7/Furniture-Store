package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.dao.ItemRepository;
import com.sudhi7.springboot.furnitureStore.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findItemById(int id) {
       Optional<Item> result = itemRepository.findById(id);
       Item item = null;
       if(result.isPresent()) {
           item = result.get();
       }
       else {
           throw new RuntimeException("Did not find item id - " + id);
       }
       return item;
    }

    @Override
    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }
}