package Hieu_iceTea.FoodMate_Spring.repository;


import Hieu_iceTea.FoodMate_Spring.model.Product;

import java.util.List;


//@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends BaseRepository<Product, Integer> {

    List<Product> findAllByNameContainsOrderByIdDesc(String name);
    //List<Product> findAllByNameContainsOrderByIdDesc(@Param("name") String name);
    //API: http://localhost:8080/api/products/search/findAllByNameContainsOrderByIdDesc?name=us

    //List<Product> findAllByNameContainsIgnoreCaseOrderByIdDesc(String name);

}