package com.example.grocery_system.service;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    CategoryService categoryService;

    @Test
    public void shouldAddUpperCaseCategory(){
        //Arrange
        Category category = new Category("produce", "grocery");
        Category category1 = new Category("toiletries", "toilet");

        List<Category> categories = new ArrayList<>();
        categories.add(category1);

        doReturn(categories).when(categoryRepository).findAll();
        doReturn(category).when(categoryRepository).save(category);

        //Act
        Category result = categoryService.addCategory(category);

        //Assert
        assertNotNull(result);
        assertEquals("PRODUCE",result.getName());
        assertEquals(category,result);

    }

    @Test
    public void shouldThrowExceptionWhenCategoryAlreadyExists(){
        //Arrange
        Category category = new Category("produce", "grocery");
        Category category1 = new Category("produce", "grocery");

        List<Category> categories = new ArrayList<>();
        categories.add(category1);

        doReturn(categories).when(categoryRepository).findAll();

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.addCategory(category);
        });

        String expectedMessage = "This category already exists: produce";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldReturnCategoryByName(){
        //Arrange
        Category category = new Category("PRODUCE", "grocery");

        doReturn(Optional.of(category)).when(categoryRepository).findByName(category.getName());

        //Act
        Category result = categoryService.getCategoryByName(category.getName());

        //Assert
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    public void shouldThrowExceptionWhenNoCategoryByName(){
        //Arrange
        doReturn(Optional.empty()).when(categoryRepository).findByName("InvalidName");

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryByName("InvalidName");
        });

        String expectedMessage = "Category was not found with name: InvalidName";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void shouldReturnCategoryById(){
        //Arrange
        Category category = new Category("PRODUCE", "grocery");

        doReturn(Optional.of(category)).when(categoryRepository).findById(category.getId());

        //Act
        Category result = categoryService.getCategoryById(category.getId());

        //Assert
        assertNotNull(result);
        assertEquals(category.getId(), result.getId());
        assertEquals(category,result);
    }

    @Test
    public void shouldThrowExceptionWhenNoCategoryById(){
        //Arrange
        doReturn(Optional.empty()).when(categoryRepository).findById(1L);

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.getCategoryById(1L);
        });

        String expectedMessage = "Category was not found with ID: 1";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void shouldUpdateCategory(){
        //Arrange
        Category category = new Category("produce", "grocery");

        doReturn(true).when(categoryRepository).existsById(category.getId());
        doReturn(category).when(categoryRepository).save(category);

        //Act
        Category result = categoryService.updateCategory(category.getId(), category);

        //Assert
        assertNotNull(result);
        assertEquals(category, result);
    }

    @Test
    public void shouldNotUpdateCategoryWithInvalidId(){
        //Arrange
        Category category = new Category("produce", "grocery");

        doReturn(false).when(categoryRepository).existsById(category.getId());

        //Act
        Category result = categoryService.updateCategory(category.getId(), category);

        //Assert
        assertNull(result);
    }

    @Test
    public void shouldThrowExceptionWhenUpdatedCategoryAlreadyExists(){
        //Arrange
        Category category = new Category("produce", "grocery");
        Category category1 = new Category("produce", "grocery");

        List<Category> categories = new ArrayList<>();
        categories.add(category1);

        doReturn(true).when(categoryRepository).existsById(category.getId());
        doReturn(categories).when(categoryRepository).findAll();

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.updateCategory(category.getId(), category);
        });

        String expectedMessage = "This category already exists: produce";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void shouldDeleteCategory(){
        //Arrange
        doReturn(true).when(categoryRepository).existsById(anyLong());

        //Act
        boolean flag = categoryService.deleteCategory(1L);

        //Assert
        assertTrue(flag);
    }

    @Test
    public void shouldNotDeleteCategoryWithInvalidId(){
        //Arrange
        doReturn(false).when(categoryRepository).existsById(anyLong());

        //Act
        boolean flag = categoryService.deleteCategory(1L);

        //Assert
        assertFalse(flag);
    }

    @Test
    public void shouldReturnAllCategories(){
        //Arrange
        Category category = new Category("produce", "grocery");
        Category category1 = new Category("toiletries", "toilet");

        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        doReturn(categories).when(categoryRepository).findAll();

        //Act
        List<Category> result = categoryService.getAllCategories();

        //Assert
        assertNotNull(result);
        assertEquals(categories, result);
    }

    @Test
    public void shouldReturnAllCategoriesSortedAsc(){
        //Arrange
        Category category = new Category("produce", "grocery");
        Category category1 = new Category("toiletries", "toilet");

        List<Category> categories = new ArrayList<>();
        categories.add(category);
        categories.add(category1);

        doReturn(categories).when(categoryRepository).findAll(Sort.by(Sort.Direction.ASC,"name"));

        //Act
        List<Category> result = categoryService.getCategoriesSortedByAsc();

        //Assert
        assertEquals(categories,result);
        assertEquals(categories.getFirst().getName(), result.getFirst().getName());
        assertEquals(categories.getLast().getName(), result.getLast().getName());
    }

    @Test
    public void shouldReturnAllCategoriesSortedDesc(){
        //Arrange
        Category category = new Category("produce", "grocery");
        Category category1 = new Category("toiletries", "toilet");

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category);

        doReturn(categories).when(categoryRepository).findAll(Sort.by(Sort.Direction.DESC,"name"));

        //Act
        List<Category> result = categoryService.getCategoriesSortedByDesc();

        //Assert
        assertEquals(categories,result);
        assertEquals(categories.getFirst().getName(), result.getFirst().getName());
        assertEquals(categories.getLast().getName(), result.getLast().getName());
    }
}
