package com.example.grocery_system.repository;

import com.example.grocery_system.model.Category;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
=======
import org.springframework.data.jpa.repository.JpaRepository;

>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
<<<<<<< HEAD

    List<Category> findAll(Sort sort);
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
}
