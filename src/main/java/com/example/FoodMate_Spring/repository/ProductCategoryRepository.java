package com.example.FoodMate_Spring.repository;


import com.example.FoodMate_Spring.model.ProductCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Integer> {

    public List<ProductCategory> findAll();

    public List<ProductCategory> findAllByOrderByIdDesc();

    public List<ProductCategory> findAllByNameContainsOrderByIdDesc(String name);

}