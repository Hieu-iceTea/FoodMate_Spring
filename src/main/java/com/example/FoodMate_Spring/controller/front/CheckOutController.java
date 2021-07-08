package com.example.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/checkout")
public class CheckOutController {

    @GetMapping(path = {"", "/", "/index"})
    public String index() {

        return "front/checkout/index";

    }

    public String addOrder() {

        return "";

    }

    public String result() {

        return "";

    }

    public String sendEmail() {

        return "";

    }

}
