package com.example.FoodMate_Spring.repository;


import com.example.FoodMate_Spring.model.Product;

import java.util.List;


public interface ProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findAllByNameContainsOrderByIdDesc(String name);

    //List<Product> findAllByNameContainsIgnoreCaseOrderByIdDesc(String name);

}