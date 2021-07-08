package com.example.FoodMate_Spring.service.productCategory;


import com.example.FoodMate_Spring.model.ProductCategory;
import com.example.FoodMate_Spring.service.base.BaseService;

import java.util.List;

public interface ProductCategoryService extends BaseService<ProductCategory, Integer> {

    List<ProductCategory> findAllByNameContainsOrderByIdDesc(String name);

    List<ProductCategory> getAll(String KeywordSearch);

}
