package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    //region - Autowired Service -
    @Qualifier("cartServiceImplement_List")
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model) {

        List<Cart> carts = cartService.content();
        model.addAttribute("carts", carts);

        return "front/cart";

    }
    //endregion


    //region - Create & Update -
    @GetMapping(path = {"/add/{id}", "/add/{id}/"})
    public String add(@PathVariable int id) {

        Product product = productService.findById(id);

        cartService.add(product, 1, product.getPrice(), null);

        return "redirect:/cart";

    }

    @GetMapping(path = {"/update/{rowId}", "/update/{rowId}/"})
    public String update(@PathVariable String rowId, @RequestParam int qty) {

        cartService.update(rowId, qty);

        return "redirect:/cart";

    }
    //endregion


    //region - Delete & Destroy -
    @GetMapping(path = {"/delete/{rowId}", "/delete/{rowId}/"})
    public String delete(@PathVariable String rowId) {

        cartService.remove(rowId);

        return "redirect:/cart";

    }

    @GetMapping(path = {"/destroy", "/destroy/"})
    public String destroy() {

        cartService.destroy();

        return "redirect:/cart";

    }
    //endregion

}
