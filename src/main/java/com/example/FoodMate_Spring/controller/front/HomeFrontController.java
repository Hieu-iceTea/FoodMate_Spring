package com.example.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeFrontController {
    @GetMapping(path = {"", "/", "/index"})
    public String index() {

        return "front/index";
    }
}
