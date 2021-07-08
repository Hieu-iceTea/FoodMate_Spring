package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin/report")
public class ReportController {

    //region - Autowired Service -
    /*@Autowired
    private ReportService reportService;*/
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        /*List<Order> reports = reportService.getAll(search);

        model.addAttribute("reports", reports);*/

        return "dashboard/report/index";
    }
    //endregion

}
