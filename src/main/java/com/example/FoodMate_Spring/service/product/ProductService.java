package com.example.FoodMate_Spring.service.product;

import com.example.FoodMate_Spring.model.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();

    public Product findById(int id);

    public void save(Product product);

    public void deleteById(int id);

    public List<Product> findAllByOrderByIdDesc();
    public List<Product> findAllByNameContainsOrderByIdDesc(String name);

    public List<Product> getAll(String KeywordSearch);
}
