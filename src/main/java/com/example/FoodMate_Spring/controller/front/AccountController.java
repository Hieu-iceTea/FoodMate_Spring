package com.example.FoodMate_Spring.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/account")
public class AccountController {

    //region - Account -
    @GetMapping("login")
    public String login() {

        return "front/account/login";

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
