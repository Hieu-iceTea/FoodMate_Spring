package com.example.FoodMate_Spring.service.product;


import com.example.FoodMate_Spring.model.Product;
import com.example.FoodMate_Spring.service.base.BaseService;

import java.util.List;

public interface ProductService extends BaseService<Product, Integer> {

    List<Product> findAllByNameContainsOrderByIdDesc(String name);

    List<Product> getAll(String KeywordSearch);

}
