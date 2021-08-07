package Hieu_iceTea.FoodMate_Spring.controller.advice;

import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//https://docs.spring.io/spring-framework/docs/4.1.6.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-controller-advice

@ControllerAdvice(basePackages = "Hieu_iceTea.FoodMate_Spring.controller.front") //Target all Controllers within specific packages
public class FrontControllerAdvice {

    //region - Autowired Service -
    @Qualifier("cartServiceImplement_List")
    @Autowired
    CartService cartService;
    //endregion

    //region - ModelAttribute -
    @ModelAttribute()
    public void getCurrentCarts(Model model) {
        model.addAttribute("_cart_content", cartService.content());

        model.addAttribute("_cart_count", cartService.count());
        model.addAttribute("_cart_total", cartService.total());
        //model.addAttribute("_cart_subtotal", cartService.subtotal());
    }
    //endregion

}