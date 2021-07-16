package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import Hieu_iceTea.FoodMate_Spring.service.productCategory.ProductCategoryService;
import Hieu_iceTea.FoodMate_Spring.service.restaurant.RestaurantService;
import Hieu_iceTea.FoodMate_Spring.utilities.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/product")
public class ProductController {

    private final String _path = "src/main/resources/static/" + "front/data-images/products";

    //region - Autowired Service -
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StorageService storageService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Product> products = productService.getAll(search);

        model.addAttribute("products", products);

        return "dashboard/product/index";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        Product product = productService.findById(id);

        model.addAttribute("product", product);

        return "dashboard/product/show";
    }
    //endregion


    //region - Create -
    @GetMapping(path = {"/create/", "/create"})
    public String create(Model model) {

        if (model.getAttribute("product") == null) { //Phục vụ cho việc Xử lý Form-Validation
            model.addAttribute("product", new Product());
        }

        model.addAttribute("productCategories", productCategoryService.findAllByOrderByIdDesc());
        model.addAttribute("restaurants", restaurantService.findAllByOrderByIdDesc());

        return "dashboard/product/create-edit";
    }

    @PostMapping(path = {"/create/", "/create"})
    public String store(@Valid @ModelAttribute Product product,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                        @RequestParam("image_file") MultipartFile file) {

        //Xử lý Validating-Form
        if (bindingResult.hasErrors()) {
            //https://stackoverflow.com/questions/2543797/spring-redirect-after-post-even-with-validation-errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            redirectAttributes.addFlashAttribute("product", product);

            return "redirect:/admin/product/create";

            /*model.addAttribute("productCategories", productCategoryService.findAllByOrderByIdDesc());
            model.addAttribute("restaurants", restaurantService.findAllByOrderByIdDesc());

            return "dashboard/product/create-edit";*/
        }

        //Xử lý file
        if (!file.isEmpty()) {
            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _path);
            product.setImage(fileName);
        }

        productService.save(product);

        return "redirect:/admin/product/index";
    }
    //endregion


    //region - Edit -
    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(Model model, @PathVariable int id) {

        if (model.getAttribute("product") == null) { //Phục vụ cho việc Xử lý Form-Validation
            model.addAttribute("product", productService.findById(id));
        }

        model.addAttribute("productCategories", productCategoryService.findAllByOrderByIdDesc());
        model.addAttribute("restaurants", restaurantService.findAllByOrderByIdDesc());

        return "dashboard/product/create-edit";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@Valid @ModelAttribute Product product,
                         BindingResult bindingResult, RedirectAttributes redirectAttributes,
                         @RequestParam("image_file") MultipartFile file,
                         @RequestParam("image_old") String fileName_old) {

        //Xử lý Form-Validation
        if (bindingResult.hasErrors()) {
            //https://stackoverflow.com/questions/2543797/spring-redirect-after-post-even-with-validation-errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            redirectAttributes.addFlashAttribute("product", product);

            return "redirect:/admin/product/" + product.getId() + "/edit";

            /*model.addAttribute("productCategories", productCategoryService.findAllByOrderByIdDesc());
            model.addAttribute("restaurants", restaurantService.findAllByOrderByIdDesc());

            return "dashboard/product/create-edit";*/
        }

        //Xử lý file
        if (!file.isEmpty()) {
            // 01. Xóa file cũ:
            storageService.delete(fileName_old, _path);

            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _path);
            product.setImage(fileName);
        }

        productService.save(product);

        return "redirect:/admin/product/" + product.getId();
    }
    //endregion


    //region - Delete -
    @DeleteMapping(path = {"/{id}/", "/{id}"})
    public String delete(@PathVariable int id) {

        // 01. Xóa file:
        storageService.delete(productService.findById(id).getImage(), _path);

        // 02. Xóa bản ghi database
        productService.deleteById(id);

        return "redirect:/admin/product/index";
    }
    //endregion

}
