package Hieu_iceTea.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    public String add() {

        return "";

    }

    @GetMapping(path = {"", "/", "/index"})
    public String index() {

        return "front/cart";

    }

    public String delete() {

        return "";

    }

    public String destroy() {

        return "";

    }

    public String update() {

        return "";

    }

}
