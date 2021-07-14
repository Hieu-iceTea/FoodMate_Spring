package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.service;

import Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model.Cart;

import java.util.HashMap;
import java.util.List;

public interface CartService {

    Cart add(int id, String name, int qty, double price, double weight, HashMap<String, Object> options);

    List<Cart> content();

    Cart update(String rowId, int qty);

    void remove(String rowId);

    void destroy();

    int count();

    double total();

    double subtotal();

}
