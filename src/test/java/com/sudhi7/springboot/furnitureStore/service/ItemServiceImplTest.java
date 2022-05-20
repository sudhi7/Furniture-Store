package com.sudhi7.springboot.furnitureStore.service;

import com.sudhi7.springboot.furnitureStore.dao.ItemRepository;
import com.sudhi7.springboot.furnitureStore.entity.Category;
import com.sudhi7.springboot.furnitureStore.entity.Item;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class ItemServiceImplTest {

    @Autowired
    ItemService itemService;

    @MockBean
    ItemRepository itemRepository;

    @Test
    void ShouldReturnAllItemsWhenHasItemsTest() {
        List<Item> items = new ArrayList<>();
        Category category = new Category(1, "Beds");
        items.add(new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category));
        when(itemRepository.findAll()).thenReturn(items);
        assertEquals(items, itemService.findAll());
    }

    @Test
    void ShouldReturnNullWhenHasNoItemsTest() {
        List<Item> items = null;
        when(itemRepository.findAll()).thenReturn(items);
        assertEquals(items, itemService.findAll());
    }

    @Test
    void ShouldReturnItemWhenGivenExistingIdTest() {
        Category category = new Category(1, "Beds");
        Item item = new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category);
        when(itemRepository.findById(1)).thenReturn(Optional.of(item));
        assertEquals(item, itemService.findItemById(1));
    }

    @Test
    void ShouldThrowExceptionWhenGivenNonExistingIdTest() throws RuntimeException{
        Optional<Item> item = Optional.empty();
        when(itemRepository.findById(2)).thenReturn(item);
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            itemService.findItemById(2);
        });
        assertEquals("Did not find item id - " + 2, thrown.getMessage());
    }

    @Test
    void ShouldSaveGivenCategoryTest() {
        Category category = new Category(1, "Beds");
        Item item = new Item(1, "Copal Queen size Bed with Box Storage in Wenge Finish", "Trevi Furniture", 35, 62, 85, 95, "Brown", 12, "Engineered Wood", 18786, "https://ii1.pepperfry.com/media/catalog/product/c/o/800x880/copal-queen-size-bed-with-box-storage-in-wenge-finish-by-trevi-furniture-copal-queen-size-bed-with-b-voafia.jpg", category);
        itemService.save(item);
        verify(itemRepository,times(1)).save(item);
    }

    @Test
    void ShouldDeleteGivenCategoryTest() {
        itemService.deleteItem(1);
        verify(itemRepository,times(1)).deleteById(1);
    }

}