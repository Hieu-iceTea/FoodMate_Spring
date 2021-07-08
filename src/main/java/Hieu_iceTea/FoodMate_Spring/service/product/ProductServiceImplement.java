package Hieu_iceTea.FoodMate_Spring.service.product;


import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.repository.ProductRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImplement extends BaseServiceImplement<Product, Integer> implements ProductService {

    //region Initialization - Autowired
    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImplement(ProductRepository repository) {
        super(repository);
    }
    //endregion

    @Override
    public List<Product> findAllByNameContainsOrderByIdDesc(String name) {
        return productRepository.findAllByNameContainsOrderByIdDesc(name);
    }

    @Override
    public List<Product> getAll(String KeywordSearch) {
        List<Product> products;
        if (KeywordSearch == null) {
            products = productRepository.findAllByOrderByIdDesc();
        } else {
            products = productRepository.findAllByNameContainsOrderByIdDesc(KeywordSearch);
        }

        return products;
    }
}
