package com.example.FoodMate_Spring.repository;


import com.example.FoodMate_Spring.model.ProductCategory;

import java.util.List;


public interface ProductCategoryRepository extends BaseRepository<ProductCategory, Integer>  {

    List<ProductCategory> findAllByNameContainsOrderByIdDesc(String name);

}