package Hieu_iceTea.FoodMate_Spring.repository;


import Hieu_iceTea.FoodMate_Spring.model.Restaurant;

import java.util.List;


public interface RestaurantRepository extends BaseRepository<Restaurant, Integer> {

    List<Restaurant> findAllByNameContainsOrderByIdDesc(String name);

}