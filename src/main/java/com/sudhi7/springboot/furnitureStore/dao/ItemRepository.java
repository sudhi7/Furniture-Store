package com.sudhi7.springboot.furnitureStore.dao;

import com.sudhi7.springboot.furnitureStore.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
