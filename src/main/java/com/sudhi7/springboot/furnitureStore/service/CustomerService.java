package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.entity.Customer;
import com.sudhi7.springboot.furnitureStore.utilities.CustomerSignUp;

import java.util.List;

public interface CustomerService {

    public List<Customer> findAll();

    public void save(CustomerSignUp customer);

    public void save(Customer customer);

    public void deleteCustomer(int id);

    public Customer findCustomerById(int id);

    public boolean isNewEmail(String email);

    public Customer findCustomerByEmail(String email);

    public String getCurrentUserEmail();

    public int getCustomerIdByEmail(String email);

    public Customer encodePassword(Customer customer);

}
