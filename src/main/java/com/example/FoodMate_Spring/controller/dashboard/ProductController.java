package com.example.FoodMate_Spring.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

    @GetMapping(path = {"", "/", "/index"})
    public String index() {
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
