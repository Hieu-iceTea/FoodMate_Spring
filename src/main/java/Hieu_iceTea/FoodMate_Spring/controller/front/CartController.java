package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        // 01. Lấy dữ liệu
        List<Cart> carts = cartService.content();

        // 02. Xử lý dữ liệu
        Map<Object, List<Cart>> cartsGroupByRestaurant_Map = carts.stream()
                .collect(Collectors.groupingBy(cart -> cart.getOptions().get("restaurant_id")));
        List<List<Cart>> cartsGroupByRestaurant = cartsGroupByRestaurant_Map.values().stream().toList();
        /*List<Cart>[] cartsGroupByRestaurant = (List<Cart>[]) cartsGroupByRestaurant_Map.values().toArray();*/


        // 03. Gửi dữ liệu đến view
        /*model.addAttribute("carts", carts);
        model.addAttribute("cartsGroupByRestaurant_Map", cartsGroupByRestaurant_Map);*/
        model.addAttribute("cartsGroupByRestaurant", cartsGroupByRestaurant);

        model.addAttribute("total", cartService.total());
        model.addAttribute("subtotal", cartService.subtotal());

        return "front/cart";

    }
    //endregion


    //region - Create & Update -
    @GetMapping(path = {"/add/{id}", "/add/{id}/"})
    public @ResponseBody ResponseEntity<HashMap<String, Object>> add(@PathVariable int id) {

        Product product = productService.findById(id);

        // 01. Khởi tạo 'options'
        HashMap<String, Object> options = new HashMap<>();
        options.put("image", product.getImage());
        options.put("restaurant_name", product.getRestaurant().getName());
        options.put("restaurant_id", product.getRestaurant().getId());

        // 02. Thêm sản phẩm vào giỏ hàng
        Cart cart = cartService.add(product.getId(), product.getName(), 1, product.getPrice(), 0.0, options);

        // 03. Khởi tạo 'response'
        HashMap<String, Object> response = new HashMap<>();
        response.put("cart", cart);
        response.put("count", cartService.count());
        response.put("total", cartService.total());


        /*ObjectMapper mapper = new ObjectMapper();
        byte[] ajaxResponse = new byte[0];
        try {
            ajaxResponse = mapper.writeValueAsBytes(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/

        return ResponseEntity.ok(response);

        //return "redirect:/cart";

    }

    @GetMapping(path = {"/update/{rowId}", "/update/{rowId}/"})
    public @ResponseBody ResponseEntity<HashMap<String, Object>> update(@PathVariable String rowId, @RequestParam int qty) {

        // 01. Cập nhật giỏ hàng
        Cart cart = cartService.update(rowId, qty);

        // 02. Khởi tạo 'response'
        HashMap<String, Object> response = new HashMap<>();
        response.put("cart", cart);
        response.put("count", cartService.count());
        response.put("total", cartService.total());
        response.put("subtotal", cartService.subtotal());

        return ResponseEntity.ok(response);

        //return "redirect:/cart";

    }
    //endregion


    //region - Delete & Destroy -
    @GetMapping(path = {"/delete/{rowId}", "/delete/{rowId}/"})
    public ResponseEntity<HashMap<String, Object>> delete(@PathVariable String rowId) {

        // 01. Xóa item giỏ hàng
        cartService.remove(rowId);

        // 02. Khởi tạo 'response'
        HashMap<String, Object> response = new HashMap<>();
        response.put("rowId_deleted", rowId);
        response.put("count", cartService.count());
        response.put("total", cartService.total());

        return ResponseEntity.ok(response);

        //return "redirect:/cart";

    }

    @GetMapping(path = {"/destroy", "/destroy/"})
    public String destroy(@RequestParam(value = "rowIds[]", required = false) String[] rowIds) {

        if (rowIds != null && rowIds.length > 0) {
            for (String rowId : rowIds) {
                cartService.remove(rowId);
            }
        } else {
            cartService.destroy();
        }

        return "redirect:/cart";

    }
    //endregion

}
