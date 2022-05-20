package com.sudhi7.springboot.furnitureStore.dao;

import com.sudhi7.springboot.furnitureStore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    public List<Order> findByCustomerIdAndIsPlaced(int customerId, boolean isPlaced);

}
