package com.example.FoodMate_Spring.service.restaurant;


import com.example.FoodMate_Spring.model.Restaurant;
import com.example.FoodMate_Spring.service.base.BaseService;

import java.util.List;

public interface RestaurantService extends BaseService<Restaurant, Integer> {

    List<Restaurant> findAllByNameContainsOrderByIdDesc(String name);

    List<Restaurant> getAll(String KeywordSearch);

}
