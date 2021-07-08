package com.example.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/feedback")
public class FeedbackController {

    @GetMapping(path = {"", "/", "/index"})
    public String index() {

        return "front/feedback/index";

    }

    public String addFeedback() {

        return "";

    }

    public String result() {

        return "";

    }

}
