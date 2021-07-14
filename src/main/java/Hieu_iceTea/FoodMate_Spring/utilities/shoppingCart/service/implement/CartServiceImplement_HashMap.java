package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.implement;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImplement_HashMap implements CartService {

    private HttpSession session;
    //private List<Cart> carts;


    public CartServiceImplement_HashMap(HttpSession session) {
        this.session = session;
    }

    private HashMap<Integer, Cart> getCarts() {
        HashMap<Integer, Cart> carts = (HashMap<Integer, Cart>) session.getAttribute("carts");

        if (carts == null) {
            carts = new HashMap<>();
        }

        return carts;
    }

    private void setCarts(HashMap<Integer, Cart> carts) {
        session.setAttribute("carts", carts);
    }

    @Override
    public Cart add(int id, String name, int qty, double price, double weight, HashMap<String, Object> options) {
        return null;
    }

    @Override
    public List<Cart> content() {
        return this.getCarts().values().stream().toList();
    }

    @Override
    public Cart update(String rowId, int qty) {
        return null;
    }

    @Override
    public void remove(String rowId) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public double total() {
        return 0;
    }

    @Override
    public double subtotal() {
        return 0;
    }

}
