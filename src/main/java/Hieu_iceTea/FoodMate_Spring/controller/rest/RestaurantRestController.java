package Hieu_iceTea.FoodMate_Spring.controller.rest;


import Hieu_iceTea.FoodMate_Spring.controller.rest.exception.RestaurantNotFoundException;
import Hieu_iceTea.FoodMate_Spring.model.Restaurant;
import Hieu_iceTea.FoodMate_Spring.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/restApi/restaurants")
public class RestaurantRestController {

    //TODO: Sửa lỗi vòng lặp đệ quy khi xử lý JSON, lý do relationship giữa các bảng.
    //Video hướng dẫn của cô ThiDK: http://youtube.com/watch?v=pMxgLOPe_OE
    //Video hướng dẫn của cô ThiDK - video 2: https://www.youtube.com/watch?v=nimev8Djyd8

    //region - Autowired Service -
    @Autowired
    private RestaurantService restaurantService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public List<Restaurant> index(@RequestParam(required = false) String search) {

        return restaurantService.getAll(search);

    }

    @GetMapping(path = {"/{id}", "/{id}/"})
    public Restaurant show(@PathVariable int id) {

        Restaurant restaurant = restaurantService.findById(id);

        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant id not found - " + id);
            //throw new RuntimeException("Restaurant id not found - " + id);
        }

        return restaurant;

    }
    //endregion


    //region - Create -
    @PostMapping(path = {"", "/"})
    public Restaurant store(@RequestBody Restaurant restaurant) {

        restaurant.setId(0);

        return restaurantService.save(restaurant);

    }
    //endregion


    //region - Edit -
    @PutMapping(path = {"/{id}", "/{id}/"})
    public Restaurant update(@RequestBody Restaurant restaurant) {

        return restaurantService.save(restaurant);

    }
    //endregion


    //region - Delete -
    @DeleteMapping(path = {"/{id}", "/{id}/"})
    public String delete(@PathVariable int id) {

        if (restaurantService.findById(id) == null) {
            throw new RestaurantNotFoundException("Restaurant id not found - " + id);
            //throw new RuntimeException("Restaurant id not found - " + id);
        }

        // 02. Xóa bản ghi database
        restaurantService.deleteById(id);

        return "Deleted restaurant id - " + id;

    }
    //endregion

}
