package com.example.grocery_system.service;

import com.example.grocery_system.model.Category;
import com.example.grocery_system.model.GroceryItem;
import com.example.grocery_system.repository.CategoryRepository;
import com.example.grocery_system.repository.GroceryRepository;
import com.example.grocery_system.util.PixabayImageSearch;
<<<<<<< HEAD
import org.springframework.data.domain.Sort;
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroceryService {
    private final PixabayImageSearch pixabayImageSearch;
    private final GroceryRepository groceryRepository;
    private final CategoryRepository categoryRepository;
    private final Random random = new Random();
    private Map<String, Object[]> possibleGroceries;

<<<<<<< HEAD
    GroceryService(PixabayImageSearch pixabayImageSearch,
                   GroceryRepository groceryRepository,
                   CategoryRepository categoryRepository) {
=======
    GroceryService(PixabayImageSearch pixabayImageSearch, GroceryRepository groceryRepository, CategoryRepository categoryRepository) {
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        this.pixabayImageSearch = pixabayImageSearch;
        this.groceryRepository = groceryRepository;
        this.categoryRepository = categoryRepository;
    }

    public void seedGroceryItems() {
        try {
            if (groceryRepository.count() > 0) {
                groceryRepository.deleteAll();
            }

            possibleGroceries = generatePossibleGroceries();
            for (int i = 0; i < possibleGroceries.size(); i++) {
                GroceryItem randomItem = createRandomGroceryItem();
                addGroceryItem(randomItem);
            }

            System.out.println("Grocery items seeded successfully");
        } catch (Exception e) {
            System.err.println("Failed to seed grocery items: " + e.getMessage());
        }
    }

    private Map<String, Object[]> generatePossibleGroceries() {
        Map<String, Object[]> groceries = new LinkedHashMap<>();

        Category fruits = categoryRepository.findByName("FRUITS").orElseThrow();
        Category vegetables = categoryRepository.findByName("VEGETABLES").orElseThrow();
        Category dairy = categoryRepository.findByName("DAIRY").orElseThrow();
        Category bakery = categoryRepository.findByName("BAKERY").orElseThrow();
        Category snacks = categoryRepository.findByName("SNACKS").orElseThrow();

        groceries.put("Apple", new Object[]{5.0, fruits});
        groceries.put("Banana", new Object[]{10.0, fruits});
        groceries.put("Carrot", new Object[]{10.0, vegetables});
        groceries.put("Donut", new Object[]{13.5, bakery});
        groceries.put("Egg", new Object[]{40.0, dairy});
        groceries.put("Flour", new Object[]{20.0, bakery});
        groceries.put("Grape", new Object[]{30.0, fruits});
        groceries.put("Honey", new Object[]{50.0, snacks});
        groceries.put("Ice Cream", new Object[]{40.0, snacks});
        groceries.put("Juice", new Object[]{30.0, snacks});
        groceries.put("Kale", new Object[]{24.5, vegetables});
        groceries.put("Lemons", new Object[]{10.5, fruits});
        groceries.put("Milk", new Object[]{28.0, dairy});
        groceries.put("Nuts", new Object[]{40.0, snacks});
        groceries.put("Onions", new Object[]{10.0, vegetables});
        groceries.put("Potatoes", new Object[]{20.0, vegetables});
        groceries.put("Quinoa", new Object[]{40.5, snacks});
        groceries.put("Rice", new Object[]{30.0, snacks});
        groceries.put("Spinach", new Object[]{20.0, vegetables});
        groceries.put("Tomatoes", new Object[]{20.5, vegetables});
        groceries.put("Ube", new Object[]{30.0, vegetables});
        groceries.put("Vanilla", new Object[]{40.0, bakery});
        groceries.put("Watermelon", new Object[]{60.0, fruits});
        groceries.put("Xanthan Gum", new Object[]{50.5, snacks});
        groceries.put("Yogurt", new Object[]{20.5, dairy});
        groceries.put("Zucchini", new Object[]{20.0, vegetables});

        return groceries;
    }

    public GroceryItem createRandomGroceryItem() {
        String name = generateUniqueName();

        Object[] groceryData = possibleGroceries.get(name);
        double price = (double) groceryData[0];
        Category category = (Category) groceryData[1];
        int quantity = 1 + random.nextInt(20);
        String imageUrl = generateImageUrl(name);

        return new GroceryItem(null, name, price, quantity, category, imageUrl);
    }

    private String generateUniqueName() {
        for (String name : possibleGroceries.keySet()) {
            boolean exists = groceryRepository.findByName(name) != null;
            if (!exists) {
                return name;
            }
        }
        List<String> availableNames = new ArrayList<>(possibleGroceries.keySet());
        return availableNames.get(random.nextInt(availableNames.size())) + "_" + random.nextInt(10000);
    }

    public String generateImageUrl(String groceryName) {
        try {
            return pixabayImageSearch.getPixabayImageUrl(groceryName);
        } catch (Exception e) {
<<<<<<< HEAD
            System.err.println("Failed to get Pixabay Image for "
                    + groceryName + ": " + Arrays.toString(e.getStackTrace()));
=======
            System.err.println("Failed to get Pixabay Image for " + groceryName + ": " + Arrays.toString(e.getStackTrace()));
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
            return null;
        }
    }

<<<<<<< HEAD
    public List<GroceryItem> getGroceriesSortedByNameAsc() {
        for(GroceryItem item : groceryRepository.findAll()){
            setDefaultImageUrl(item);
        }
        return groceryRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public List<GroceryItem> getGroceriesSortedByNameDesc() {
        for(GroceryItem item : groceryRepository.findAll()){
            setDefaultImageUrl(item);
        }
        return groceryRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
    }

    public List<GroceryItem> getAllGroceryItems() {
        for(GroceryItem item : groceryRepository.findAll()){
            setDefaultImageUrl(item);
=======
    public List<GroceryItem> getAllGroceryItems() {
        for(GroceryItem item : groceryRepository.findAll()){
            if (item.getImageUrl() == null || item.getImageUrl().isBlank()) {
                item.setImageUrl(generateImageUrl(item.getName()));
            }
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
        }
        return groceryRepository.findAll();
    }

<<<<<<< HEAD
    public void setDefaultImageUrl(GroceryItem item){
        if (item.getImageUrl() == null || item.getImageUrl().isBlank()) {
            item.setImageUrl(generateImageUrl(item.getName()));
        }
    }

=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
    public GroceryItem getGroceryItemById(String id) {
        return groceryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grocery item not found with ID: " + id));
    }

    public GroceryItem addGroceryItem(GroceryItem item) {
<<<<<<< HEAD
        if(checksIfGroceryNameExists(item)){
            throw new RuntimeException("This grocery already exists: " + item.getName());
        }
        setDefaultImageUrl(item);
        return groceryRepository.save(item);
    }

    public boolean checksIfGroceryNameExists(GroceryItem groceryItem){
        for(GroceryItem item : groceryRepository.findAll()){
            if(groceryItem.getName().equalsIgnoreCase(item.getName())){
                if(groceryItem.getId().equals(item.getId())){
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItem updatedItem) {
        if (groceryRepository.existsById(id.toString())) {
            if(checksIfGroceryNameExists(updatedItem)){
                throw new RuntimeException("This grocery already exists: " + updatedItem.getName());
            }
            updatedItem.setId(id);
            setDefaultImageUrl(updatedItem);
=======
        if (item.getImageUrl() == null || item.getImageUrl().isBlank()) {
            item.setImageUrl(generateImageUrl(item.getName()));
        }
        return groceryRepository.save(item);
    }

    public GroceryItem updateGroceryItem(Long id, GroceryItem updatedItem) {
        if (groceryRepository.existsById(id.toString())) {
            updatedItem.setId(id);
            if (updatedItem.getImageUrl() == null || updatedItem.getImageUrl().isBlank()) {
                updatedItem.setImageUrl(generateImageUrl(updatedItem.getName()));
            }
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
            return groceryRepository.save(updatedItem);
        }
        return null;
    }

    public boolean deleteGroceryItem(Long id) {
        if (groceryRepository.existsById(id.toString())) {
            groceryRepository.deleteById(id.toString());
            return true;
        }
        return false;
    }

    public List<GroceryItem> searchGroceries(String keyword) {
        return groceryRepository.findByNameContainingIgnoreCase(keyword);
    }
<<<<<<< HEAD

    public List<GroceryItem> filterGroceriesByCategory(Category category){
        return groceryRepository.findByCategory(category);
    }
=======
>>>>>>> aea768050c8b39c347dfeba62a25b6b8079d851e
}