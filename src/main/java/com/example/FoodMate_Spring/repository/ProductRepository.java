package com.example.FoodMate_Spring.repository;

import com.example.FoodMate_Spring.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    public List<Product> findAllByOrderByNameAsc();

    public List<Product> findAll();

    public List<Product> findAllByOrderByIdDesc();

    public List<Product> findAllByNameContainsIgnoreCaseOrderByIdDesc(String name);
    public List<Product> findAllByNameContainsOrderByIdDesc(String name);
}