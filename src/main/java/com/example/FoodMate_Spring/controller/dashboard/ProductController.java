package com.example.FoodMate_Spring.controller.dashboard;

import com.example.FoodMate_Spring.model.Product;
import com.example.FoodMate_Spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model) {
        List<Product> products = productService.findAllByOrderByIdDesc();

        model.addAttribute("products", products);

        return "dashboard/product/index";
    }

    @GetMapping(path = {"/create/", "/create"})
    public String create() {
        return "dashboard/product/create-edit";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(@PathVariable int id) {
        return "dashboard/product/show";
    }

    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(@PathVariable int id) {
        return "dashboard/product/create-edit";
    }

}
