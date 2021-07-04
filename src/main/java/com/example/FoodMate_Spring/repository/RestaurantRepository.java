package com.example.FoodMate_Spring.repository;


import com.example.FoodMate_Spring.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface RestaurantRepository extends CrudRepository<Restaurant, Integer> {

    public List<Restaurant> findAll();

    public List<Restaurant> findAllByOrderByIdDesc();

    public List<Restaurant> findAllByNameContainsOrderByIdDesc(String name);

}