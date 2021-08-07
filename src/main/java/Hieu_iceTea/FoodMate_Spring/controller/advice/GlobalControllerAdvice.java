package Hieu_iceTea.FoodMate_Spring.controller.advice;

import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//https://docs.spring.io/spring-framework/docs/4.1.6.RELEASE/spring-framework-reference/html/mvc.html#mvc-ann-controller-advice
@ControllerAdvice()
public class GlobalControllerAdvice {

    //region - Autowired Service -
    @Autowired
    UserService userService;
    //endregion

    //region - ModelAttribute -
    //https://stackoverflow.com/questions/30121565/spring-mvc-thymeleaf-adding-variable-to-all-templates-context
    @ModelAttribute("_auth_currentUser")
    public User getCurrentUser() {

        return userService.getCurrentUser();

    }
    //endregion

}