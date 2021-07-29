package Hieu_iceTea.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    //region - Account -
    @GetMapping("login")
    public String login() {

        return "front/account/login";

    }

    //Nguồn: https://stackoverflow.com/questions/45709333/page-redirecting-depending-on-role-using-spring-security-and-thymeleaf-spring
    //Tham khảo cách khác tại: https://stackoverflow.com/questions/42738090/spring-role-based-redirecting-after-login
    @GetMapping("/login-success-redirect")
    public void loginSuccessRedirect(HttpServletRequest request, HttpServletResponse response/*, Authentication authResult*/) throws IOException, ServletException {

        /*String role = authResult.getAuthorities().toString();
        if (role.contains("ROLE_HOST") || role.contains("ROLE_ADMIN") || role.contains("ROLE_STAFF")) {*/

        if (request.isUserInRole("HOST") || request.isUserInRole("ADMIN") || request.isUserInRole("STAFF")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        } else {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
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
    public String myOrderIndex() {

        return "front/account/my-order/index";

    }

    @GetMapping("my-order/{id}")
    public String myOrderShow(@PathVariable int id) {

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
