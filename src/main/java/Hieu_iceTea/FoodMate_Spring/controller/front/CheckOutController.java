package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/checkout")
public class CheckOutController {

    //region - Autowired Service -
    @Qualifier("cartServiceImplement_List")
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model) {

        model.addAttribute("currentUser", userService.getCurrentUser());

        model.addAttribute("carts", cartService.content());
        model.addAttribute("total", cartService.total());
        model.addAttribute("subtotal", cartService.subtotal());

        return "front/checkout/index";

    }
    //endregion


    //region - Add new -
    public String addOrder() {

        return "";

    }

    public String result() {

        return "";

    }
    //endregion


    public String sendEmail() {

        return "";

    }

}
