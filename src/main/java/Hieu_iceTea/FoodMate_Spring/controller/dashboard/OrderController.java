package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/order")
public class OrderController {

    //region - Autowired Service -
    @Autowired
    private OrderService orderService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Order> orders = orderService.findAllByOrderByIdDesc();
        //List<Order> orders = orderService.getAll(search);

        model.addAttribute("orders", orders);

        return "dashboard/order/index";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        Order order = orderService.findById(id);

        model.addAttribute("order", order);

        return "dashboard/order/show";
    }
    //endregion

}
