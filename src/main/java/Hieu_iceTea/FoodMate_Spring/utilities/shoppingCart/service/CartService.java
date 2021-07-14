package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;

import java.util.HashMap;
import java.util.List;

public interface CartService {

    void add(Product product, int qty, double price, HashMap<String, Object> options);

    List<Cart> content();

    void update(String rowId, int qty);

    void remove(String rowId);

    void destroy();

    int count();

    double total();

    double subtotal();

}
