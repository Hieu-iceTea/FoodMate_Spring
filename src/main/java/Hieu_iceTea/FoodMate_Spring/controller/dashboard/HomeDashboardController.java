package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class HomeDashboardController {

    @GetMapping(path = {"", "/", "/index"})
    public String index() {

        return "dashboard/index";
    }

}
