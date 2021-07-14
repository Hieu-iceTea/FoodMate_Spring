package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.implement;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.utilities.Common;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImplement_List implements CartService {

    //region - Autowired -
    @Autowired
    private HttpSession session;
    //endregion


    //region - Common Method (Getter, Setter) -
    private List<Cart> getCarts() {
        List<Cart> carts = (List<Cart>) session.getAttribute("carts");

        if (carts == null) {
            carts = new ArrayList<>();
        }

        return carts;
    }

    private void setCarts(List<Cart> carts) {
        session.setAttribute("carts", carts);
    }

    private Cart getCartByProductId(int id) {
        List<Cart> carts = this.getCarts();

        return carts.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private Cart getCartByRowId(String rowId) {
        List<Cart> carts = this.getCarts();

        return carts.stream()
                .filter(c -> c.getRowId().equals(rowId))
                .findFirst()
                .orElse(null);
    }

    private Cart setCartQty(Cart cart, int qty) {
        List<Cart> carts = this.getCarts();

        int index = carts.indexOf(cart);
        cart.setQty(qty);
        carts.set(index, cart);

        this.setCarts(carts);

        return cart;
    }
    //endregion


    //region - Public - Main Method -
    @Override
    public Cart add(int id, String name, int qty, double price, double weight, HashMap<String, Object> options) {
        List<Cart> carts = this.getCarts();

        //Check if the product is in the List<Cart>
        Cart cart = this.getCartByProductId(id);

        if (cart == null) {
            Cart newCart = new Cart(id, name, weight, qty, price, options);

            carts.add(newCart);
            this.setCarts(carts);

            return newCart;
        } else {
            this.setCartQty(cart, cart.getQty() + qty);

            return cart;
        }
    }

    @Override
    public List<Cart> content() {
        return this.getCarts();
    }

    @Override
    public Cart update(String rowId, int qty) {
        Cart cart = this.getCartByRowId(rowId);

        return this.setCartQty(cart, qty);
    }

    @Override
    public void remove(String rowId) {
        List<Cart> carts = this.getCarts();

        Cart cart = this.getCartByRowId(rowId);

        carts.remove(cart);

        this.setCarts(carts);
    }

    @Override
    public void destroy() {
        /*List<Cart> carts = this.getCarts();

        carts.clear();

        this.setCarts(carts);*/


        this.setCarts(new ArrayList<>());
    }

    @Override
    public int count() {
        List<Cart> carts = this.getCarts();

        return carts.stream()
                .map(Cart::getQty)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public double total() {
        List<Cart> carts = this.getCarts();

        double total = carts.stream()
                .map(cart -> (cart.getPrice() * cart.getQty()))
                .reduce(Double::sum)
                .orElse(0.0);

        total = Common.round(total, 2); //Làm tròn đến chữ số thập phân thứ 2

        return total;
    }

    @Override
    public double subtotal() {
        return this.total(); //Tạm thời làm thế này đã, hihi.
    }
    //endregion

}
