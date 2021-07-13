package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.model.ProductCategory;
import Hieu_iceTea.FoodMate_Spring.model.Restaurant;
import Hieu_iceTea.FoodMate_Spring.service.restaurant.RestaurantService;
import Hieu_iceTea.FoodMate_Spring.utilities.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/restaurant")
public class RestaurantController {

    //region - Autowired Service -
    @Autowired
    private RestaurantService restaurantService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Restaurant> restaurants = restaurantService.getAll(search);

        model.addAttribute("restaurants", restaurants);

        return "front/restaurant/index";

    }

    @GetMapping(path = {"/{id}/", "/{id}", "/{id}/{slug}.html"})
    public String show(Model model, @PathVariable int id, @PathVariable(required = false) String slug) {

        // Restaurant info
        Restaurant restaurant = restaurantService.findById(id);

        if  (slug == null || slug.isBlank()) {
            slug = Common.toSlug(restaurant.getName());
            return "redirect:/restaurant/" + id + "/" + slug + ".html";
        }

        model.addAttribute("restaurant", restaurant);

        // Product list of this restaurant:
        List<Product> products = restaurant.getProducts();
        model.addAttribute("products", products);

        List<ProductCategory> productCategories = products.stream()
                .map(Product::getProductCategory)
                .sorted(Comparator.comparingInt(ProductCategory::getId))
                .distinct()
                .toList();
        model.addAttribute("productCategories", productCategories);

        return "front/restaurant/show";

    }
    //endregion

}
