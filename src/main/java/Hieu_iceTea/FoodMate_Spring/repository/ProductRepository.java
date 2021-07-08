package Hieu_iceTea.FoodMate_Spring.repository;


import Hieu_iceTea.FoodMate_Spring.model.Product;

import java.util.List;


public interface ProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findAllByNameContainsOrderByIdDesc(String name);

    //List<Product> findAllByNameContainsIgnoreCaseOrderByIdDesc(String name);

}