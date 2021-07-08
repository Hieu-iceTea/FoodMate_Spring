package com.example.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/menu")
public class MenuController {

    @GetMapping(path = {"", "/", "/index"})
    public String index() {

        return "front/menu/index";

    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(@PathVariable int id) {

        return "front/menu/show";

    }

}
