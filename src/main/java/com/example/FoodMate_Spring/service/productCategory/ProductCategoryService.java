package com.example.FoodMate_Spring.service.productCategory;


import com.example.FoodMate_Spring.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    public List<ProductCategory> findAll();

    public ProductCategory findById(int id);

    public void save(ProductCategory productCategory);

    public void deleteById(int id);

    public List<ProductCategory> findAllByOrderByIdDesc();

    public List<ProductCategory> findAllByNameContainsOrderByIdDesc(String name);

    public List<ProductCategory> getAll(String KeywordSearch);

}
