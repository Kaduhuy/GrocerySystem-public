package com.example.grocery_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD

    @Column(unique = true, nullable = false)
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
    private String name;
    private double price;
    private int quantity;
    private String unit;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public GroceryItem(){}

    public GroceryItem(Long id, String name, double price, int quantity, Category category){
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }
    public GroceryItem(Long id, String name, double price, int quantity, Category category, String imageUrl){
        this(id, name, price, quantity, category);
        this.imageUrl = imageUrl;
    }
}
