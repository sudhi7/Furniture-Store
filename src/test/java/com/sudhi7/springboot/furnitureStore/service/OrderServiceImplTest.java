package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.dao.CustomerRepository;
import com.sudhi7.springboot.furnitureStore.dao.OrderRepository;
import com.sudhi7.springboot.furnitureStore.entity.Category;
import com.sudhi7.springboot.furnitureStore.entity.Customer;
import com.sudhi7.springboot.furnitureStore.entity.Item;
import com.sudhi7.springboot.furnitureStore.entity.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    void ShouldReturnAllOrdersWhenHasOrdersTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,true, customer, items);
        orders.add(order);
        when(orderRepository.findAll()).thenReturn(orders);
        assertEquals(orders, orderService.findAll());
    }

    @Test
    void ShouldReturnNullWhenHasNoOrdersTest() {
        List<Order> orders = null;
        when(orderRepository.findAll()).thenReturn(orders);
        assertEquals(orders, orderService.findAll());
    }

    @Test
    void ShouldReturnOrderWhenGivenExistingIdTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,true, customer, items);
        orders.add(order);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        assertEquals(order, orderService.findOrderById(1));
    }

    @Test
    void ShouldThrowExceptionWhenGivenNonExistingIdTest() throws RuntimeException{
        Optional<Order> order = Optional.empty();
        when(orderRepository.findById(2)).thenReturn(order);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            orderService.findOrderById(2);
        });
        assertEquals("Did not find Order Id - " + 2, thrown.getMessage());
    }

    @Test
    void ShouldReturnCustomerOrdersTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,true, customer, items);
        orders.add(order);
        when(orderRepository.findByCustomerIdAndIsPlaced(1,true)).thenReturn(orders);
        assertEquals(orders, orderService.getCustomerOrders(1));
    }

    @Test
    void ShouldReturnCustomerCartTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,false, customer, items);
        orders.add(order);
        when(orderRepository.findByCustomerIdAndIsPlaced(1,false)).thenReturn(orders);
        assertEquals(orders, orderService.getCustomerCart(1));
    }

    @Test
    void ShouldSaveGivenOrderTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        Order order = new Order(1,false, customer, items);
        orderService.save(order);
        verify(orderRepository,times(1)).save(order);
    }

    @Test
    void ShouldFindCartOrderByIdTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        Customer customer = new Customer(1,"Elon","Musk","elon@gmail.com","Elon@123");
        List<Order> orders = new ArrayList<>();
        Order order = new Order(1,false, customer, items);
        orders.add(order);
        when(orderRepository.findByCustomerIdAndIsPlaced(1, false)).thenReturn(orders);
        assertEquals(order, orderService.findCartOrder(1));
    }


}