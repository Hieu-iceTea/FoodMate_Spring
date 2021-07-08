package Hieu_iceTea.FoodMate_Spring.controller.front;

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

    @GetMapping(path = {"/contact", "/contact/"})
    public String contact() {

        return "front/contact";
    }

    @GetMapping(path = {"/about", "/about/"})
    public String about() {

        return "front/about";
    }

}
