package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service.implement;

import Hieu_iceTea.FoodMate_Spring.model.Product;
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


    //region - Common Method -
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
                .filter(c -> c.getProduct().getId() == id)
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

    private void setCartQty(Cart cart, int qty) {
        List<Cart> carts = this.getCarts();

        int index = carts.indexOf(cart);
        cart.setQty(qty);
        carts.set(index, cart);

        this.setCarts(carts);
    }
    //endregion


    //region - Public - Main Method -
    @Override
    public void add(Product product, int qty, double price, HashMap<String, Object> options) {

        List<Cart> carts = this.getCarts();

        //Check if the product is in the List<Cart>
        Cart cart = this.getCartByProductId(product.getId());

        if (cart == null) {
            Cart newCart = new Cart(product, qty, price, options);

            carts.add(newCart);
            this.setCarts(carts);

        } else {
            this.setCartQty(cart, cart.getQty() + qty);
        }
    }

    @Override
    public List<Cart> content() {
        return this.getCarts();
    }

    @Override
    public void update(String rowId, int qty) {
        Cart cart = this.getCartByRowId(rowId);

        this.setCartQty(cart, qty);
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

        return carts.stream()
                .map(Cart::getPrice)
                .reduce(Double::sum)
                .orElse(0.0);
    }

    @Override
    public double subtotal() {
        return this.total();
    }
    //endregion

}
