package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.BaseModel;
import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.model.ProductCategory;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import Hieu_iceTea.FoodMate_Spring.service.productCategory.ProductCategoryService;
import Hieu_iceTea.FoodMate_Spring.service.restaurant.RestaurantService;
import Hieu_iceTea.FoodMate_Spring.utilities.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping(path = "/menu")
public class MenuController {

    //region - Autowired Service -
    @Autowired
    private ProductService productService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model, @RequestParam(required = false) String search) { //Có thể bỏ @RequestParam nếu dùng [required = false]

        List<Product> products = productService.getAll(search);
        model.addAttribute("products", products);

        //List<ProductCategory> productCategories = productCategoryService.findAll();
        List<ProductCategory> productCategories = products.stream()
                .map(Product::getProductCategory)
                //.sorted((o1, o2) -> ((Integer)o1.getId()).compareTo(o2.getId())) //Muốn sắp xếp ngược lại thì đảo [o1, o2] ở vế sau
                //.sorted((o1, o2) -> Integer.compare(o1.getId(), o2.getId()))
                //.sorted(Comparator.comparingInt(productCategory -> productCategory.getId()))
                .sorted(Comparator.comparingInt(ProductCategory::getId))
                .distinct()
                .toList();
        model.addAttribute("productCategories", productCategories);

        return "front/menu/index";

    }

    @GetMapping(path = {"/{id}/", "/{id}", "/{id}/{slug}.html"})
    public String show(Model model, @PathVariable int id, @PathVariable(required = false) String slug) {

        Product product = productService.findById(id);

        if  (slug == null || slug.isBlank()) {
            slug = Common.toSlug(product.getName());
            return "redirect:/menu/" + id + "/" + slug + ".html";
        }

        model.addAttribute("product", product);

        return "front/menu/show";

    }
    //endregion

}
