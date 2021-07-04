package com.example.FoodMate_Spring.service.productCategory;


import com.example.FoodMate_Spring.model.ProductCategory;
import com.example.FoodMate_Spring.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductCategoryServiceImplement implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findAllByOrderByIdDesc() {
        return productCategoryRepository.findAllByOrderByIdDesc();
    }

    @Override
    public ProductCategory findById(int id) {
        Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(id);

        ProductCategory productCategory;
        if (productCategoryOptional.isPresent()) {
            productCategory = productCategoryOptional.get();
        } else {
            throw new RuntimeException("Did not find item id - " + id);
        }

        return productCategory;
    }

    @Override
    public void save(ProductCategory product) {
        productCategoryRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productCategoryRepository.deleteById(id);
    }

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
