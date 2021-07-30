package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Feedback;
import Hieu_iceTea.FoodMate_Spring.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/feedback")
public class FeedbackController {

    //region - Autowired Service -
    @Autowired
    private FeedbackService feedbackService;
    //endregion

    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model) {

        if (model.getAttribute("feedback") == null) { //Phục vụ cho việc Xử lý Form-Validation
            model.addAttribute("feedback", new Feedback());
        }

        return "front/feedback/index";

    }
    //endregion

    //region - Add new -
    @PostMapping(path = {"", "/", "/index"})
    public String addFeedback(@Valid @ModelAttribute Feedback feedback,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //Xử lý Validating-Form
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.feedback", bindingResult);
            redirectAttributes.addFlashAttribute("feedback", feedback);

            return "redirect:/feedback";
        }

        feedbackService.save(feedback);

        redirectAttributes.addFlashAttribute("message", "We take note of your feedback.");

        return "redirect:/feedback/result";

    }

    @GetMapping(path = {"result", "result/"})
    public String result(Model model) {

        String message = (String) model.getAttribute("message");

        if (message == null || message.isBlank()) {
            return "redirect:/";
        }

        return "front/feedback/result";

    }
    //endregion

}
