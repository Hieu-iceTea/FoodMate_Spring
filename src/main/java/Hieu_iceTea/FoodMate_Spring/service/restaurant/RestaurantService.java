package Hieu_iceTea.FoodMate_Spring.service.restaurant;


import Hieu_iceTea.FoodMate_Spring.model.Restaurant;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseService;

import java.util.List;

public interface RestaurantService extends BaseService<Restaurant, Integer> {

    List<Restaurant> findAllByNameContainsOrderByIdDesc(String name);

    List<Restaurant> getAll(String KeywordSearch);

}
