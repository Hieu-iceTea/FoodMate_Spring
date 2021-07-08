package com.example.FoodMate_Spring.controller.dashboard;

import com.example.FoodMate_Spring.model.Restaurant;
import com.example.FoodMate_Spring.service.restaurant.RestaurantService;
import com.example.FoodMate_Spring.utilities.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/restaurant")
public class RestaurantDashboardController {

    private final String _imagePath = "src/main/resources/static/" + "front/data-images/restaurants";

    //region - Autowired Service -
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StorageService storageService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Restaurant> restaurants = restaurantService.getAll(search);

        model.addAttribute("restaurants", restaurants);

        return "dashboard/restaurant/index";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        Restaurant restaurant = restaurantService.findById(id);

        model.addAttribute("restaurant", restaurant);

        return "dashboard/restaurant/show";
    }
    //endregion


    //region - Create -
    @GetMapping(path = {"/create/", "/create"})
    public String create(Model model) {

        model.addAttribute("restaurant", new Restaurant());

        return "dashboard/restaurant/create-edit";
    }

    @PostMapping(path = {"", "/"})
    public String store(@ModelAttribute Restaurant restaurant, @RequestParam("image_file") MultipartFile file) {

        //Xử lý file
        if (!file.isEmpty()) {
            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _imagePath);
            restaurant.setImage(fileName);
        }

        restaurantService.save(restaurant);

        return "redirect:/admin/restaurant/index";
    }
    //endregion


    //region - Edit -
    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(Model model, @PathVariable int id) {

        model.addAttribute("restaurant", restaurantService.findById(id));

        return "dashboard/restaurant/create-edit";
    }

    @PostMapping(path = {"/{id}/", "/{id}"})
    public String update(@ModelAttribute Restaurant restaurant, @RequestParam("image_file") MultipartFile file, @RequestParam("image_old") String fileName_old) {

        //Xử lý file
        if (!file.isEmpty()) {
            // 01. Xóa file cũ:
            storageService.delete(fileName_old, _imagePath);

            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _imagePath);
            restaurant.setImage(fileName);
        }

        restaurantService.save(restaurant);

        return "redirect:/admin/restaurant/" + restaurant.getId();
    }
    //endregion


    //region - Delete -
    @DeleteMapping(path = {"/{id}/", "/{id}"})
    public String delete(@PathVariable int id) {

        // 01. Xóa file:
        storageService.delete(restaurantService.findById(id).getImage(), _imagePath);

        // 02. Xóa bản ghi database
        restaurantService.deleteById(id);

        return "redirect:/admin/restaurant/index";
    }
    //endregion

}
