package com.example.FoodMate_Spring.service.restaurant;


import com.example.FoodMate_Spring.model.Restaurant;
import com.example.FoodMate_Spring.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImplement implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> findAllByOrderByIdDesc() {
        return restaurantRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Restaurant findById(int id) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(id);

        Restaurant restaurant;
        if (optionalRestaurant.isPresent()) {
            restaurant = optionalRestaurant.get();
        } else {
            throw new RuntimeException("Did not find item id - " + id);
        }

        return restaurant;
    }

    @Override
    public void save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteById(int id) {
        restaurantRepository.deleteById(id);
    }

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
