package com.example.FoodMate_Spring.service;

import com.example.FoodMate_Spring.model.Product;
import com.example.FoodMate_Spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findAllByOrderByIdDesc() {
        return productRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Product findById(int id) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product product;

        if (productOptional.isPresent()) {
            product = productOptional.get();
        } else {
            throw new RuntimeException("Did not find item id - " + id);
        }

        return product;
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }
}
