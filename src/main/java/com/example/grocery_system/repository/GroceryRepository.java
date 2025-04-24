package com.example.grocery_system.repository;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.model.GroceryItem;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<GroceryItem, String> {

    List<GroceryItem> findByCategory(Category category);

    GroceryItem findByName(String name);

    List<GroceryItem> findByNameContainingIgnoreCase(String keyword);
<<<<<<< HEAD

    List<GroceryItem> findAll(Sort sort);
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
}
