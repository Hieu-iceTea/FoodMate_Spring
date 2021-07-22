package Hieu_iceTea.FoodMate_Spring.controller.restApi;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/restApi/products")
public class ProductControllerApi {

    //TODO: Sửa lỗi vòng lặp đệ quy khi xử lý JSON, lý do relationship giữa các bảng.
    //Video hướng dẫn của cô ThiDK: http://youtube.com/watch?v=pMxgLOPe_OE

    //region - Autowired Service -
    @Autowired
    private ProductService productService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public List<Product> index(@RequestParam(required = false) String search) {

        return productService.getAll(search);

    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public Product show(@PathVariable int id) {

        return productService.findById(id);

    }
    //endregion


    //region - Create -
    @PostMapping(path = {"/create/", "/create"})
    public Product store(@Valid @ModelAttribute Product product,
                        BindingResult bindingResult, RedirectAttributes redirectAttributes,
                        @RequestParam("image_file") MultipartFile file) {

        //Xử lý Validating-Form
        if (bindingResult.hasErrors()) {
            //https://stackoverflow.com/questions/2543797/spring-redirect-after-post-even-with-validation-errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.product", bindingResult);
            redirectAttributes.addFlashAttribute("product", product);

            //return "redirect:/admin/product/create";

            /*model.addAttribute("productCategories", productCategoryService.findAllByOrderByIdDesc());
            model.addAttribute("restaurants", restaurantService.findAllByOrderByIdDesc());

            return "dashboard/product/create-edit";*/
        }

        //Xử lý file
        if (!file.isEmpty()) {
            // 02. Lưu file mới:
            /*String fileName =  storageService.store(file, _path);
            product.setImage(fileName);*/
        }

        return productService.save(product);

        //return "redirect:/admin/product/index";
    }
    //endregion


    //region - Edit -
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
            /*storageService.delete(fileName_old, _path);*/

            // 02. Lưu file mới:
            /*String fileName =  storageService.store(file, _path);
            product.setImage(fileName);*/
        }

        productService.save(product);

        return "redirect:/admin/product/" + product.getId();
    }
    //endregion


    //region - Delete -
    @DeleteMapping(path = {"/{id}/", "/{id}"})
    public void delete(@PathVariable int id) {

        // 01. Xóa file:
        //storageService.delete(productService.findById(id).getImage(), _path);

        // 02. Xóa bản ghi database
        productService.deleteById(id);

        //return "redirect:/admin/product/index";
        //return productService.findAll();
    }
    //endregion

}
