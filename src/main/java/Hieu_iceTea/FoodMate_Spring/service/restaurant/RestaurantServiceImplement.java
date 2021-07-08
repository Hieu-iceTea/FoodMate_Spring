package Hieu_iceTea.FoodMate_Spring.service.restaurant;


import Hieu_iceTea.FoodMate_Spring.model.Restaurant;
import Hieu_iceTea.FoodMate_Spring.repository.RestaurantRepository;
import Hieu_iceTea.FoodMate_Spring.service.base.BaseServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantServiceImplement extends BaseServiceImplement<Restaurant, Integer> implements RestaurantService {

    //region Initialization - Autowired
    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImplement(RestaurantRepository repository) {
        super(repository);
    }
    //endregion

    @Override
    public List<Restaurant> findAllByNameContainsOrderByIdDesc(String name) {
        return restaurantRepository.findAllByNameContainsOrderByIdDesc(name);
    }

    @Override
    public List<Restaurant> getAll(String KeywordSearch) {
        List<Restaurant> restaurants;
        if (KeywordSearch == null) {
            restaurants = restaurantRepository.findAllByOrderByIdDesc();
        } else {
            restaurants = restaurantRepository.findAllByNameContainsOrderByIdDesc(KeywordSearch);
        }

        return restaurants;
    }

}
