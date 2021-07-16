package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.model.OrderDetail;
import Hieu_iceTea.FoodMate_Spring.service.order.OrderService;
import Hieu_iceTea.FoodMate_Spring.service.orderDetail.OrderDetailService;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import Hieu_iceTea.FoodMate_Spring.service.restaurant.RestaurantService;
import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import Hieu_iceTea.FoodMate_Spring.utilities.Common;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping(path = "/checkout")
public class CheckOutController {

    //region - Autowired Service -
    @Qualifier("cartServiceImplement_List")
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RestaurantService restaurantService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public String index(Model model) {

        model.addAttribute("currentUser", userService.getCurrentUser());

        model.addAttribute("carts", cartService.content());
        model.addAttribute("total", cartService.total());
        model.addAttribute("subtotal", cartService.subtotal());

        return "front/checkout/index";

    }
    //endregion


    //region - Add new -
    @PostMapping(path = {"", "/", "/index"})
    public String addOrder(RedirectAttributes redirectAttributes, @RequestParam("delivery_address") String deliveryAddress) {

        // 01. Xử lý dữ liệu giỏ hàng
        List<Cart> content_carts = cartService.content();

        Map<Object, List<Cart>> cartsGroupByRestaurant_Map = content_carts.stream()
                .collect(Collectors.groupingBy(cart -> cart.getOptions().get("restaurant_id")));
        List<List<Cart>> cartsGroupByRestaurant = cartsGroupByRestaurant_Map.values().stream().toList();


        // 02. Xử lý Đơn hàng
        for (List<Cart> carts : cartsGroupByRestaurant) {
            // 02.A. Order
            Order order = new Order();

            order.setDeliveryAddress(deliveryAddress);
            order.setPaymentType(1); //TODO
            double total = carts.stream()
                    .map(cart -> (cart.getPrice() * cart.getQty()))
                    .reduce(Double::sum)
                    .orElse(0.0);
            total = Common.round(total, 2); //Làm tròn đến chữ số thập phân thứ 2
            order.setTotalAmount(total);
            order.setStatus(1); //TODO

            order.setUser(userService.getCurrentUser());
            order.setRestaurant(restaurantService.findById((Integer) carts.get(0).getOptions().get("restaurant_id")));

            order = orderService.save(order);

            // 02.B. OrderDetail - List
            List<OrderDetail> orderDetails = new ArrayList<>();

            for (Cart cart : carts) {
                OrderDetail orderDetail = new OrderDetail();

                orderDetail.setQty(cart.getQty());
                orderDetail.setAmount(cart.getPrice());
                orderDetail.setTotalAmount(cart.getQty() * cart.getPrice());

                orderDetail.setOrder(order);
                orderDetail.setProduct(productService.findById(cart.getId()));

                orderDetails.add(orderDetail);
            }

            orderDetailService.saveAll(orderDetails);
        }

        // 03. Xóa giỏ hàng
        cartService.destroy();

        // 04. Gửi mail

        // 05. Gửi thông báo
        redirectAttributes.addFlashAttribute("message", "Please check your email. You will receive it soon...");

        return "redirect:/checkout/result";

    }

    @GetMapping(path = {"result", "result/"})
    public String result(Model model) {

        String message = (String) model.getAttribute("message");
        //@ModelAttribute("message") String message
        //String message = (String) model.asMap().get("message");
        //String message = (String) RequestContextUtils.getInputFlashMap(request).get("message"); //HttpServletRequest request

        if (message == null || message.isBlank()) {
            return "redirect:/";
        }

        return "front/checkout/result";

    }
    //endregion


    public String sendEmail() {

        return "";

    }

}
