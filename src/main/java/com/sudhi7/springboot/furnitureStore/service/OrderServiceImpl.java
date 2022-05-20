package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.dao.CustomerRepository;
import com.sudhi7.springboot.furnitureStore.dao.ItemRepository;
import com.sudhi7.springboot.furnitureStore.dao.OrderRepository;
import com.sudhi7.springboot.furnitureStore.entity.Customer;
import com.sudhi7.springboot.furnitureStore.entity.Item;
import com.sudhi7.springboot.furnitureStore.entity.Order;
import com.sudhi7.springboot.furnitureStore.service.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findOrderById(int id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order = null;
        if(result.isPresent()) {
            order = result.get();
        }
        else {
            throw new RuntimeException("Did not find Order Id - " + id);
        }
        return order;
    }

    @Override
    public List<Order> getCustomerOrders(Integer customerId) {
        return orderRepository.findByCustomerIdAndIsPlaced(customerId, true);
    }

    @Override
    public List<Order> getCustomerCart(Integer customerId) {
        return orderRepository.findByCustomerIdAndIsPlaced(customerId, false);
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void createOrder(int customerId) {
        Optional<Customer> customerResult = customerRepository.findById(customerId);
        if(customerResult.isPresent()) {
            Customer customer = customerResult.get();
            Order order = new Order(false, customer, new ArrayList<Item>());
            save(order);
        }
    }

    @Override
    public void createOrder(String email) {
        Customer customer = customerRepository.findByEmail(email);
        Order order = new Order(false, customer, new ArrayList<Item>());
        save(order);
    }

    @Override
    public Order findCartOrder(int customerId) {
        List<Order> orders = orderRepository.findByCustomerIdAndIsPlaced(customerId, false);
        if(orders == null || orders.size()==0)
            return null;
        return orders.get(0);
    }

}