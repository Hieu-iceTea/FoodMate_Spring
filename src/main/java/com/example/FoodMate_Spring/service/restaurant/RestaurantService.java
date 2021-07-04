package com.example.FoodMate_Spring.service.restaurant;


import com.example.FoodMate_Spring.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    public List<Restaurant> findAll();

    public Restaurant findById(int id);

    public void save(Restaurant restaurant);

    public void deleteById(int id);

    public List<Restaurant> findAllByOrderByIdDesc();

    public List<Restaurant> findAllByNameContainsOrderByIdDesc(String name);

    public List<Restaurant> getAll(String KeywordSearch);

}
