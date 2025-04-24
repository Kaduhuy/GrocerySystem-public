package com.example.grocery_system.service;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.model.GroceryItem;
<<<<<<< HEAD
import com.example.grocery_system.repository.GroceryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
=======
import com.example.grocery_system.repository.CategoryRepository;
import com.example.grocery_system.repository.GroceryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e

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

<<<<<<< HEAD
    @Captor
    ArgumentCaptor<String> imageCaptor;

=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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
<<<<<<< HEAD

        //Verify
        verify(groceryService, times(2)).generateImageUrl(anyString());
        verify(groceryService, times(2)).generateImageUrl(imageCaptor.capture());
        List<String> values = imageCaptor.getAllValues();
        assertNotNull(values);
=======
        assertNotNull(result.getFirst().getImageUrl());
        assertNotNull(result.getLast().getImageUrl());

        //Verify
        verify(groceryService, times(2)).generateImageUrl(anyString());
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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
<<<<<<< HEAD
        //Arrange
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        GroceryItem item = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
<<<<<<< HEAD
        GroceryItem item1 = new GroceryItem(
                1L,
                "apple",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        List<GroceryItem> items = new ArrayList<>();
        items.add(item1);

        doReturn(items).when(groceryRepository).findAll();
=======

>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        doReturn(item).when(groceryRepository).save(item);

        //Act
        GroceryItem result = groceryService.addGroceryItem(item);

        //Assert
        assertEquals(item, result);
    }

    @Test
<<<<<<< HEAD
    public void shouldNotAddGroceryItemWithSameName(){
        //Arrange
        GroceryItem item = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item1 = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        List<GroceryItem> items = new ArrayList<>();
        items.add(item1);

        doReturn(items).when(groceryRepository).findAll();

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            groceryService.addGroceryItem(item);
        });

        String expectedMessage = "This grocery already exists: jam";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
    public void shouldAddGroceryItemWithoutImageUrl(){
        //Arrange
        GroceryItem itemWithoutImage = new GroceryItem(
                2L,
<<<<<<< HEAD
                "mango",
=======
                "cantalope",
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
                30.0,
                5,
                new Category("FRUIT", "fruit")
        );

        doReturn(itemWithoutImage).when(groceryRepository).save(itemWithoutImage);

        //Act
        GroceryItem result = groceryService.addGroceryItem(itemWithoutImage);

        //Assert
        assertNotNull(result);
<<<<<<< HEAD

        //Verify
        verify(groceryService, times(1)).generateImageUrl(anyString());
        verify(groceryService, times(1)).generateImageUrl(imageCaptor.capture());
        String value = imageCaptor.getValue();
        assertNotNull(value);
=======
        assertNotNull(result.getImageUrl());

        //Verify
        verify(groceryService, times(1)).generateImageUrl(anyString());
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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

<<<<<<< HEAD
        GroceryItem item = new GroceryItem(
                1L,
                "apple",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        List<GroceryItem> groceries = new ArrayList<>();
        groceries.add(item);

        doReturn(groceries).when(groceryRepository).findAll();
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        doReturn(true).when(groceryRepository).existsById(updateItem.getId().toString());
        doReturn(updateItem).when(groceryRepository).save(updateItem);

        //Act
        GroceryItem result = groceryService.updateGroceryItem(updateItem.getId(),updateItem);

        //Assert
        assertNotNull(result);
        assertEquals(updateItem, result);
    }

    @Test
<<<<<<< HEAD
    public void shouldNotUpdateGroceryItemWithSameName(){
        //Arrange
        GroceryItem updateItem = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        GroceryItem item = new GroceryItem(
                1L,
                "jam",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        List<GroceryItem> groceries = new ArrayList<>();
        groceries.add(item);

        doReturn(groceries).when(groceryRepository).findAll();
        doReturn(true).when(groceryRepository).existsById(updateItem.getId().toString());

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            groceryService.updateGroceryItem(updateItem.getId(), updateItem);
        });

        String expectedMessage = "This grocery already exists: jam";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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
<<<<<<< HEAD
        verify(groceryService, times(1)).generateImageUrl(imageCaptor.capture());
        String value = imageCaptor.getValue();
        assertNotNull(value);
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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
<<<<<<< HEAD
        //Arrange
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
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

<<<<<<< HEAD
        //Act
        List<GroceryItem> result = groceryService.searchGroceries("JAM");

        //Assert
        assertEquals(list, result);
    }

    @Test
    public void shouldReturnGroceriesSortedAsc(){
        //Arrange
        GroceryItem item = new GroceryItem(
                1L,
                "apple",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item1 = new GroceryItem(
                1L,
                "banana",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item2 = new GroceryItem(
                1L,
                "carrot",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        List<GroceryItem> groceries = new ArrayList<>();
        groceries.add(item2);
        groceries.add(item1);
        groceries.add(item);

        List<GroceryItem> sortedGroceries = new ArrayList<>();
        sortedGroceries.add(item);
        sortedGroceries.add(item1);
        sortedGroceries.add(item2);

        doReturn(groceries).when(groceryRepository).findAll();
        doReturn(sortedGroceries).when(groceryRepository).findAll(Sort.by(Sort.Direction.ASC, "name"));

        //Act
        List<GroceryItem> result = groceryService.getGroceriesSortedByNameAsc();

        //Assert
        assertNotNull(result);
        assertEquals("apple",result.get(0).getName());
        assertEquals("banana", result.get(1).getName());
        assertEquals("carrot", result.get(2).getName());
    }

    @Test
    public void shouldReturnGroceriesSortedDesc(){
        //Arrange
        GroceryItem item = new GroceryItem(
                1L,
                "apple",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item1 = new GroceryItem(
                1L,
                "banana",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item2 = new GroceryItem(
                1L,
                "carrot",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        List<GroceryItem> groceries = new ArrayList<>();
        groceries.add(item);
        groceries.add(item1);
        groceries.add(item2);

        List<GroceryItem> sortedGroceries = new ArrayList<>();
        sortedGroceries.add(item2);
        sortedGroceries.add(item1);
        sortedGroceries.add(item);

        doReturn(groceries).when(groceryRepository).findAll();
        doReturn(sortedGroceries).when(groceryRepository).findAll(Sort.by(Sort.Direction.DESC, "name"));

        //Act
        List<GroceryItem> result = groceryService.getGroceriesSortedByNameDesc();

        //Assert
        assertNotNull(result);
        assertEquals("carrot",result.get(0).getName());
        assertEquals("banana", result.get(1).getName());
        assertEquals("apple", result.get(2).getName());
    }

    @Test
    public void shouldReturnGroceriesContainingSearchKeyword(){
        //Arrange
        String keyword = "a";

        GroceryItem item = new GroceryItem(
                1L,
                "apple",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item1 = new GroceryItem(
                1L,
                "banana",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );
        GroceryItem item2 = new GroceryItem(
                1L,
                "carrot",
                30.0,
                5,
                new Category("PRODUCE", "grocery"),
                "image-url"
        );

        List<GroceryItem> groceries = new ArrayList<>();
        groceries.add(item);
        groceries.add(item1);
        groceries.add(item2);

        doReturn(groceries).when(groceryRepository).findByNameContainingIgnoreCase(keyword);

        //Act
        List<GroceryItem> result = groceryService.searchGroceries(keyword);

        //Assert
        assertNotNull(result);
        assertEquals(result, groceries);
    }

    @Test
    public void shouldReturnGroceriesByCategory(){
        //Arrange
        Category category = new Category("PRODUCE", "grocery");
        GroceryItem item = new GroceryItem(
                1L,
                "apple",
                30.0,
                5,
                category,
                "image-url"
        );
        GroceryItem item1 = new GroceryItem(
                1L,
                "banana",
                30.0,
                5,
                category,
                "image-url"
        );

        List<GroceryItem> groceriesWithProduceCategory = new ArrayList<>();
        groceriesWithProduceCategory.add(item);
        groceriesWithProduceCategory.add(item1);

        doReturn(groceriesWithProduceCategory).when(groceryRepository).findByCategory(category);

        //Act
        List<GroceryItem> result = groceryService.filterGroceriesByCategory(category);

        //Assert
        assertNotNull(result);
        assertEquals(groceriesWithProduceCategory, result);
        assertEquals("PRODUCE",groceriesWithProduceCategory.get(0).getCategory().getName());
        assertEquals("PRODUCE", groceriesWithProduceCategory.get(1).getCategory().getName());
    }
=======
        List<GroceryItem> result = groceryService.searchGroceries("JAM");

        assertEquals(list, result);
    }
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
}
