package com.cabservice.MegaCity.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cabservice.MegaCity.model.Booking;
import com.cabservice.MegaCity.model.Category;
import com.cabservice.MegaCity.service.CategoryService;



@RestController
@RequestMapping("/api/category")
public class CategoryController {
      
    @Autowired
    private CategoryService catService;

    /**
     * Creates a new category.
     * @param category The category details received in the request body.
     * @return The created category.
     */

     
    @PostMapping("/createcategory")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return catService.createCategory(category);
    }

    /**
     * Retrieves a category by its ID.
     * @param catID The ID of the category to retrieve.
     * @return The category details.
     */

    @GetMapping("/{catID}")
    public Category getCategoryById(@PathVariable String catID) {
        return catService.getCategoryById(catID);
    }

    /**
     * Deletes a category by its ID.
     * @param catID The ID of the category to delete.
     * @return A message indicating the deletion status.
     */
@GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = catService.getAllCategories();
        return ResponseEntity.ok(categories);
    }


    @DeleteMapping("/{catID}")
    public String deleteCategory(@PathVariable String catID) {
        return catService.deleteCategory(catID);
    }
}
