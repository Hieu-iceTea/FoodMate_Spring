package com.example.FoodMate_Spring.repository;


import com.example.FoodMate_Spring.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;


public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {

}