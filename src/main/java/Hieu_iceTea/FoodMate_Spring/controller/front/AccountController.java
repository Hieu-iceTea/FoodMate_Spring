package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.service.order.OrderService;
import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    //region - Autowired Service -
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    //endregion


    //region - Account -
    @GetMapping("login")
    public String login(HttpSession httpSession, @RequestParam(required = false) String continueUri) {

        httpSession.setAttribute("continueUri", continueUri);

        return "front/account/login";

    }

    //Nguồn: https://stackoverflow.com/questions/45709333/page-redirecting-depending-on-role-using-spring-security-and-thymeleaf-spring
    //Tham khảo cách khác tại: https://stackoverflow.com/questions/42738090/spring-role-based-redirecting-after-login
    @GetMapping("/login-success-redirect")
    public String loginSuccessRedirect(HttpServletRequest request) throws IOException, ServletException {

        String continueUri = (String) request.getSession().getAttribute("continueUri");
        if (continueUri != null && !continueUri.isBlank()) {
            //request.getSession().removeAttribute("continueUri");

            return "redirect:/" + continueUri;
        }

        if (request.isUserInRole("HOST") || request.isUserInRole("ADMIN") ||request.isUserInRole("ADMIN_ReadOnly") || request.isUserInRole("STAFF")) {
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("access-denied")
    public String accessDenied() {

        return "front/account/access-denied";

    }

    @GetMapping("register")
    public String register() {

        return "front/account/register";

    }

    public String postRegister() {

        return "";

    }

    @GetMapping("reset-password")
    public String resetPassword() {

        return "front/account/reset-password";

    }

    public String postResetPassword() {

        return "";

    }
    //endregion


    //region - Order -
    @GetMapping("my-order")
    public String myOrderIndex(Model model) {

        List<Order> orders = orderService.findAllByUserOrderByIdDesc(userService.getCurrentUser());
        //List<Order> orders = orderService.findAllByUserIdOrderByIdDesc(userService.getCurrentUser().getId());

        model.addAttribute("orders", orders);

        return "front/account/my-order/index";

    }

    @GetMapping("my-order/{id}")
    public String myOrderShow(Model model, @PathVariable int id) {

        Order order = orderService.findById(id);

        if (order.getUser() != userService.getCurrentUser()) {
            return "redirect:/account/my-order";
        }

        model.addAttribute("order", order);

        return "front/account/my-order/show";

    }

    public String myOrderUpdate() {

        return "";

    }
    //endregion


    //region - Profile -
    @GetMapping("profile")
    public String profileShow() {

        return "front/account/profile/show";

    }

    @GetMapping("profile/edit")
    public String profileEdit() {

        return "front/account/profile/edit";

    }

    @GetMapping("profile/change-password")
    public String profileChangePassword() {

        return "front/account/profile/change-password";

    }

    public String profileUpdate() {

        return "";

    }

    public String profileDestroy() {

        return "";

    }
    //endregion

}
