package Hieu_iceTea.FoodMate_Spring.controller.front;

import Hieu_iceTea.FoodMate_Spring.model.Order;
import Hieu_iceTea.FoodMate_Spring.model.OrderDetail;
import Hieu_iceTea.FoodMate_Spring.service.order.OrderService;
import Hieu_iceTea.FoodMate_Spring.service.orderDetail.OrderDetailService;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import Hieu_iceTea.FoodMate_Spring.service.restaurant.RestaurantService;
import Hieu_iceTea.FoodMate_Spring.service.user.UserService;
import Hieu_iceTea.FoodMate_Spring.utilities.Common;
import Hieu_iceTea.FoodMate_Spring.utilities.email.EmailService;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Qualifier("emailServiceImplement_SpringMail")
    @Autowired
    private EmailService emailService;
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

        // 03. Gửi mail
        Map<String, Object> mail_data = new HashMap<>() {{
            put("carts", cartService.content());
            put("order_infos", new HashMap<>() {{
                put("fullName", userService.getCurrentUser().getLastName() + ", " + userService.getCurrentUser().getFirstName());
                put("address", deliveryAddress);
                put("phone", userService.getCurrentUser().getPhone());
                put("email", userService.getCurrentUser().getEmail());
                put("total", cartService.total());
            }});
        }};

        this.sendEmail_OrderNotification(userService.getCurrentUser().getEmail(), mail_data);

        // 04. Gửi thông báo
        redirectAttributes.addFlashAttribute("message", "Please check your email. You will receive it soon...");

        // 05. Xóa giỏ hàng
        cartService.destroy();

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


    private void sendEmail_OrderNotification(String to, Map<String, Object> mail_data) {

        // Cách 1. Gửi mail đơn giản:
        //emailService.sendSimpleMessage(userService.getCurrentUser().getEmail(), "Thông báo đơn hàng", "Bạn đã đặt hàng thành công!!!");


        // Cách 2. Gửi mail dùng template HTML (Thymeleaf)

        // 1. Template Model
        Map<String, Object> templateModel = mail_data;

        // 2. InLine Files
        List<File> inLineFiles = new ArrayList<>();
        inLineFiles.add(new File("src/main/resources/templates/mail/order-notification/images/TechWiz-header.png"));
        inLineFiles.add(new File("src/main/resources/templates/mail/order-notification/images/logo-cloud-kitchen-black.png"));

        //Product-images
        List<Cart> carts = (List<Cart>) mail_data.get("carts");
        for (Cart cart : carts) {
            String image = (String) cart.getOptions().get("image");

            inLineFiles.add(new File("src/main/resources/static/front/data-images/products/" + image));
        }

        // 3. Attachment Files
        /*List<File> attachmentFiles = new ArrayList<>();
        attachmentFiles.add(new File("src/main/resources/static/favicon-admin.png"));*/

        // 4. Gọi tới service để gửi email
        emailService.sendMessageUsingThymeleafTemplate(
                to,
                "Order Notification",
                "order-notification/index.html",
                templateModel,
                inLineFiles,
                null
        );

    }

}
