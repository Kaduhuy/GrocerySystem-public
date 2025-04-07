package com.example.grocery_system.service;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.model.GroceryItem;
import com.example.grocery_system.repository.CategoryRepository;
import com.example.grocery_system.repository.GroceryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroceryServiceTest {
    @Mock
    GroceryRepository groceryRepository;

    @Spy
    @InjectMocks
    GroceryService groceryService;

    @Test
    public void shouldReturnAllGroceryItems(){
        //Arrange
        GroceryItem item = new GroceryItem(
              1L,
              "jam",
              30.0,
              5,
                new Category("PRODUCE", "grocery"),
              "image-url"
        );

        List<GroceryItem> items = new ArrayList<>();
        items.add(item);
        doReturn(items).when(groceryRepository).findAll();

        //Act
        List<GroceryItem> result = groceryService.getAllGroceryItems();

        //Assert
        assertNotNull(result);
        assertEquals(items, result);
    }

    @Test
    public void shouldReturnGroceryItemsWithoutImageUrl(){
        //Arrange
        GroceryItem itemWithoutImage = new GroceryItem(
                2L,
                "cantalope",
                30.0,
                5,
                new Category("FRUIT", "fruit")
        );

        GroceryItem itemWithoutImage2 = new GroceryItem(
                2L,
                "carrot",
                30.0,
                5,
                new Category("VEGETABLE", "vegetable")
        );

        List<GroceryItem> items = new ArrayList<>();
        items.add(itemWithoutImage);
        items.add(itemWithoutImage2);

        doReturn(items).when(groceryRepository).findAll();

        //Act
        List<GroceryItem> result = groceryService.getAllGroceryItems();

        //Assert
        assertNotNull(result);
        assertEquals(items, result);
        assertNotNull(result.getFirst().getImageUrl());
        assertNotNull(result.getLast().getImageUrl());

        //Verify
        verify(groceryService, times(2)).generateImageUrl(anyString());
    }

    @Test
    public void shouldReturnGroceryItemById(){
        //Arrange
        GroceryItem item = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        doReturn(Optional.of(item)).when(groceryRepository).findById(item.getId().toString());

        //Act
        GroceryItem result = groceryService.getGroceryItemById(item.getId().toString());

        //Assert
        assertEquals(item, result);
        verify(groceryRepository, times(1)).findById(item.getId().toString());
    }

    @Test
    public void shouldThrowExceptionWhenInvalidGroceryItemId() throws RuntimeException{
        // Arrange
        doReturn(Optional.empty()).when(groceryRepository).findById("2");

        //Act and Assert
        Exception exception = assertThrows(RuntimeException.class, () ->{
            groceryService.getGroceryItemById("2");
        });

        String expectedMessage = "Grocery item not found with ID: 2";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldAddGroceryItem(){
        //Arrange
        GroceryItem item = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        doReturn(item).when(groceryRepository).save(item);

        //Act
        GroceryItem result = groceryService.addGroceryItem(item);

        //Assert
        assertEquals(item, result);
    }

    @Test
    public void shouldAddGroceryItemWithoutImageUrl(){
        //Arrange
        GroceryItem itemWithoutImage = new GroceryItem(
                2L,
                "cantalope",
                30.0,
                5,
                new Category("FRUIT", "fruit")
        );

        doReturn(itemWithoutImage).when(groceryRepository).save(itemWithoutImage);

        //Act
        GroceryItem result = groceryService.addGroceryItem(itemWithoutImage);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getImageUrl());

        //Verify
        verify(groceryService, times(1)).generateImageUrl(anyString());
    }

    @Test
    public void shouldUpdateGroceryItem(){
        //Arrange
        GroceryItem updateItem = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        doReturn(true).when(groceryRepository).existsById(updateItem.getId().toString());
        doReturn(updateItem).when(groceryRepository).save(updateItem);

        //Act
        GroceryItem result = groceryService.updateGroceryItem(updateItem.getId(),updateItem);

        //Assert
        assertNotNull(result);
        assertEquals(updateItem, result);
    }

    @Test
    public void shouldUpdateGroceryItemWithoutImageUrl(){
        //Arrange
        GroceryItem updateItemWithoutImage = new GroceryItem(
                2L,
                "cantalope",
                30.0,
                5,
                new Category("FRUIT", "fruit")
        );

        doReturn(true).when(groceryRepository).existsById(updateItemWithoutImage.getId().toString());
        doReturn(updateItemWithoutImage).when(groceryRepository).save(updateItemWithoutImage);

        //Act
        GroceryItem result = groceryService.updateGroceryItem(updateItemWithoutImage.getId(),updateItemWithoutImage);

        //Assert
        assertNotNull(result);
        assertEquals(updateItemWithoutImage, result);

        //Verify
        verify(groceryService, times(1)).generateImageUrl(anyString());
    }

    @Test
    public void shouldNotUpdateGroceryItemWithInvalidId(){
        GroceryItem updateItem = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        doReturn(false).when(groceryRepository).existsById(updateItem.getId().toString());

        GroceryItem result = groceryService.updateGroceryItem(updateItem.getId(),updateItem);

        assertNull(result);
    }

    @Test
    public void shouldDeleteGroceryItem(){
        //Arrange
        doReturn(true).when(groceryRepository).existsById(anyString());

        //Act
        boolean flag = groceryService.deleteGroceryItem(1L);

        //Assert
        assertTrue(flag);

    }

    @Test
    public void shouldNotDeleteGroceryItemWithInvalidId(){
        //Arrange
        doReturn(false).when(groceryRepository).existsById(anyString());

        //Act
        boolean flag = groceryService.deleteGroceryItem(1L);

        //Assert
        assertFalse(flag);

    }

    @Test
    public void shouldReturnGroceryListWhenGrocerySearch(){
        GroceryItem item = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        List<GroceryItem> list = new ArrayList<>();
        list.add(item);

        doReturn(list).when(groceryRepository).findByNameContainingIgnoreCase("JAM");

        List<GroceryItem> result = groceryService.searchGroceries("JAM");

        assertEquals(list, result);
    }
}
