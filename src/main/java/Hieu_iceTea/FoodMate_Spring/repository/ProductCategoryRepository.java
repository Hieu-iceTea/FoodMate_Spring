package Hieu_iceTea.FoodMate_Spring.repository;


import Hieu_iceTea.FoodMate_Spring.model.ProductCategory;

import java.util.List;


public interface ProductCategoryRepository extends BaseRepository<ProductCategory, Integer>  {

    List<ProductCategory> findAllByNameContainsOrderByIdDesc(String name);

}