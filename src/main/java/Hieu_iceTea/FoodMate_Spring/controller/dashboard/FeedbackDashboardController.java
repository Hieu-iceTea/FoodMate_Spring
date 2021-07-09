package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import Hieu_iceTea.FoodMate_Spring.model.Feedback;
import Hieu_iceTea.FoodMate_Spring.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/feedback")
public class FeedbackDashboardController {

    //region - Autowired Service -
    @Autowired
    private FeedbackService feedbackService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Feedback> feedbacks = feedbackService.findAllByOrderByIdDesc();
        //List<Feedback> feedbacks = feedbackService.getAll(search);

        model.addAttribute("feedbacks", feedbacks);

        return "dashboard/feedback/index";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        Feedback feedback = feedbackService.findById(id);

        model.addAttribute("feedback", feedback);

        return "dashboard/feedback/show";
    }
    //endregion

}
