package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import Hieu_iceTea.FoodMate_Spring.model.User;
import Hieu_iceTea.FoodMate_Spring.service.authority.AuthorityService;
import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import Hieu_iceTea.FoodMate_Spring.utilities.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/user")
public class UserController {

    private final String _imagePath = "src/main/resources/static/" + "front/data-images/user";

    //region - Autowired Service -
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private StorageService storageService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<User> users = userService.findAllByOrderByIdDesc();
        //List<User> users = userService.getAll(search);

        model.addAttribute("users", users);

        return "dashboard/user/index";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        User user = userService.findById(id);

        model.addAttribute("user", user);

        return "dashboard/user/show";
    }
    //endregion


    //region - Create -
    @GetMapping(path = {"/create/", "/create"})
    public String create(Model model) {

        model.addAttribute("user", new User());

        return "dashboard/user/create-edit";
    }

    @PostMapping(path = {"/create/", "/create"})
    public String store(@ModelAttribute User user,
                        BindingResult bindingResult,
                        @RequestParam("image_file") MultipartFile file) {

        //Xử lý Validating-Form
        if (bindingResult.hasErrors()) {
            return "dashboard/user/create-edit";
        }

        //Xử lý file
        if (!file.isEmpty()) {
            //Lưu file mới:
            String fileName =  storageService.store(file, _imagePath);
            user.setImage(fileName);
        }

        userService.save(user);

        return "redirect:/admin/user/index";
    }
    //endregion


    //region - Edit -
    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(Model model, @PathVariable int id) {

        model.addAttribute("user", userService.findById(id));

        return "dashboard/user/create-edit";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@ModelAttribute User user,
                         BindingResult bindingResult,
                         @RequestParam("image_file") MultipartFile file,
                         @RequestParam("image_old") String fileName_old,
                         @RequestParam(value = "arrAuthorities[]", required = false) String[] arrAuthorities) {

        //Xử lý Form-Validation
        if (bindingResult.hasErrors()) {
            return "dashboard/user/create-edit";
        }

        //Xử lý file
        if (!file.isEmpty()) {
            // 01. Xóa file cũ:
            if (fileName_old != null && !fileName_old.isBlank()) {
                storageService.delete(fileName_old, _imagePath);
            }

            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _imagePath);
            user.setImage(fileName);
        }

        //Xử lý mật khẩu:
        String password = user.getPassword();
        if (password != null && !password.isBlank()) {
            String passwordEncode = new BCryptPasswordEncoder().encode(password); //mã hóa mật khẩu kiểu 'BCrypt'

            user.setPassword("{bcrypt}" + passwordEncode);
        } else {
            user.setPassword(userService.findById(user.getId()).getPassword()); //Giữ nguyên mật khẩu hiện tại
        }

        //Gọi đến service, lưu vào database
        authorityService.updateUserAuthority(userService.findById(user.getId()), arrAuthorities);
        userService.save(user);

        return "redirect:/admin/user/" + user.getId();
    }
    //endregion


    //region - Delete -
    @DeleteMapping(path = {"/{id}/", "/{id}"})
    public String delete(@PathVariable int id) {

        // 01. Xóa file:
        String fileName_old = userService.findById(id).getImage();
        if (fileName_old != null && !fileName_old.isBlank()) {
            storageService.delete(fileName_old, _imagePath);
        }

        // 02. Xóa bản ghi database
        userService.deleteById(id);

        return "redirect:/admin/user/index";
    }
    //endregion

}
