package com.sudhi7.springboot.furnitureStore.dao;

import com.sudhi7.springboot.furnitureStore.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByEmail(String email);

}
