package com.example.FoodMate_Spring.service.productCategory;


import com.example.FoodMate_Spring.model.ProductCategory;
import com.example.FoodMate_Spring.repository.ProductCategoryRepository;
import com.example.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductCategoryServiceImplement extends BaseServiceImplement<ProductCategory, Integer> implements ProductCategoryService {

    //region Initialization - Autowired
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImplement(ProductCategoryRepository repository) {
        super(repository);
    }
    //endregion

    @Override
    public List<ProductCategory> findAllByNameContainsOrderByIdDesc(String name) {
        return productCategoryRepository.findAllByNameContainsOrderByIdDesc(name);
    }

    @Override
    public List<ProductCategory> getAll(String KeywordSearch) {
        List<ProductCategory> productCategories;
        if (KeywordSearch == null) {
            productCategories = productCategoryRepository.findAllByOrderByIdDesc();
        } else {
            productCategories = productCategoryRepository.findAllByNameContainsOrderByIdDesc(KeywordSearch);
        }

        return productCategories;
    }
}
