package com.example.FoodMate_Spring.controller.dashboard;

import com.example.FoodMate_Spring.model.Product;
import com.example.FoodMate_Spring.service.product.ProductService;
import com.example.FoodMate_Spring.service.productCategory.ProductCategoryService;
import com.example.FoodMate_Spring.service.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Product> products = productService.getAll(search);

        model.addAttribute("products", products);

        return "dashboard/product/index";
    }


    @GetMapping(path = {"/create/", "/create"})
    public String create() {
        return "dashboard/product/create-edit";
    }


    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "dashboard/product/show";
    }


    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(Model model, @PathVariable int id) {

        model.addAttribute("product", productService.findById(id));
        model.addAttribute("productCategories", productCategoryService.findAllByOrderByIdDesc());
        model.addAttribute("restaurants", restaurantService.findAllByOrderByIdDesc());

        return "dashboard/product/create-edit";
    }

    @PostMapping(path = {"/{id}/", "/{id}"})
    public String update(@ModelAttribute Product product) {

        productService.save(product);

        return "redirect:/admin/product/" + product.getId();
    }

}
