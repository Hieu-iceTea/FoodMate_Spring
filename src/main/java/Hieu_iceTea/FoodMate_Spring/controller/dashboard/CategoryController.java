package Hieu_iceTea.FoodMate_Spring.controller.dashboard;

import Hieu_iceTea.FoodMate_Spring.model.ProductCategory;
import Hieu_iceTea.FoodMate_Spring.service.productCategory.ProductCategoryService;
import Hieu_iceTea.FoodMate_Spring.utilities.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/category")
public class CategoryController {

    private final String _imagePath = "src/main/resources/static/" + "front/data-images/categories";

    //region - Autowired Service -
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private StorageService storageService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<ProductCategory> productCategories = productCategoryService.getAll(search);

        model.addAttribute("productCategories", productCategories);

        return "dashboard/category/index";
    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public String show(Model model, @PathVariable int id) {

        ProductCategory productCategory = productCategoryService.findById(id);

        model.addAttribute("productCategory", productCategory);

        return "dashboard/category/show";
    }
    //endregion


    //region - Create -
    @GetMapping(path = {"/create/", "/create"})
    public String create(Model model) {

        model.addAttribute("productCategory", new ProductCategory());

        return "dashboard/category/create-edit";
    }

    @PostMapping(path = {"/create/", "/create"})
    public String store(@ModelAttribute ProductCategory productCategory,
                        BindingResult bindingResult,
                        @RequestParam("image_file") MultipartFile file) {

        //Xử lý Validating-Form
        if (bindingResult.hasErrors()) {
            return "dashboard/category/create-edit";
        }

        //Xử lý file
        if (!file.isEmpty()) {
            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _imagePath);
            productCategory.setImage(fileName);
        }

        productCategoryService.save(productCategory);

        return "redirect:/admin/category/index";
    }
    //endregion


    //region - Edit -
    @GetMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String edit(Model model, @PathVariable int id) {

        model.addAttribute("productCategory", productCategoryService.findById(id));

        return "dashboard/category/create-edit";
    }

    @PostMapping(path = {"/{id}/edit/", "/{id}/edit"})
    public String update(@ModelAttribute ProductCategory productCategory,
                         BindingResult bindingResult,
                         @RequestParam("image_file") MultipartFile file,
                         @RequestParam("image_old") String fileName_old) {

        //Xử lý Form-Validation
        if (bindingResult.hasErrors()) {
            return "dashboard/category/create-edit";
        }

        //Xử lý file
        if (!file.isEmpty()) {
            // 01. Xóa file cũ:
            if (fileName_old != null && !fileName_old.isBlank()) {
                storageService.delete(fileName_old, _imagePath);
            }

            // 02. Lưu file mới:
            String fileName =  storageService.store(file, _imagePath);
            productCategory.setImage(fileName);
        }

        productCategoryService.save(productCategory);

        return "redirect:/admin/category/" + productCategory.getId();
    }
    //endregion


    //region - Delete -
    @DeleteMapping(path = {"/{id}/", "/{id}"})
    public String delete(@PathVariable int id) {

        // 01. Xóa file:
        String fileName_old = productCategoryService.findById(id).getImage();
        if (fileName_old != null && !fileName_old.isBlank()) {
            storageService.delete(fileName_old, _imagePath);
        }

        // 02. Xóa bản ghi database
        productCategoryService.deleteById(id);

        return "redirect:/admin/category/index";
    }
    //endregion

}
