package Hieu_iceTea.FoodMate_Spring.controller.advice;

import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//https://docs.spring.io/spring-framework/docs/4.1.6.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-controller-advice

@ControllerAdvice(basePackages = "Hieu_iceTea.FoodMate_Spring.controller.front") //Target all Controllers within specific packages
public class ControllerAdviceFront {

    //region - Autowired Service -
    @Qualifier("cartServiceImplement_List")
    @Autowired
    CartService cartService;
    //endregion

    //region - ModelAttribute -
    @ModelAttribute()
    public void getCurrentCarts(Model model) {
        model.addAttribute("carts", cartService.content());

        model.addAttribute("cartCount", cartService.count());
        model.addAttribute("cartTotal", cartService.total());
        model.addAttribute("cartSubtotal", cartService.subtotal());
    }
    //endregion

}