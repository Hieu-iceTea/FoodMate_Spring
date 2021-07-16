package Hieu_iceTea.FoodMate_Spring.controller;

import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//https://docs.spring.io/spring-framework/docs/4.1.6.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-controller-advice

//@ControllerAdvice(basePackages = "Hieu_iceTea.FoodMate_Spring.controller.front") //Target all Controllers within specific packages
@ControllerAdvice()
public class ControllerAdviceGlobal {

    //region - Autowired Service -
    @Qualifier("cartServiceImplement_List")
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;
    //endregion

    //region - ModelAttribute -
    @ModelAttribute()
    public void getCurrentCarts(Model model) {
        model.addAttribute("carts", cartService.content());

        model.addAttribute("cartCount", cartService.count());
        model.addAttribute("cartTotal", cartService.total());
        model.addAttribute("cartSubtotal", cartService.subtotal());
    }

    //https://stackoverflow.com/questions/30121565/spring-mvc-thymeleaf-adding-variable-to-all-templates-context
    @ModelAttribute("currentUser")
    public User getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails userDetails) { //principal != "anonymousUser"
                return userService.findByUsername(userDetails.getUsername());
            }
        }

        return null;

    }
    //endregion

}