package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model;

import Hieu_iceTea.FoodMate_Spring.model.Product;

import java.util.HashMap;

public class Cart {

    //region - Define Fields -
    private String rowId;

    /*private int id;
    private String name;
    private double weight;*/

    private Product product;

    private int qty;

    private double price;

    private HashMap<String, Object> options;
    //endregion


    //region - Constructor -
    public Cart() {
        //None
    }

    public Cart(Product product, int qty, double price, HashMap<String, Object> options) {
        this.rowId = "cartRowId_" + product.getId();
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.options = options;
    }

    public Cart(String rowId, Product product, int qty, double price, HashMap<String, Object> options) {
        this.rowId = rowId;
        this.product = product;
        this.qty = qty;
        this.price = price;
        this.options = options;
    }
    //endregion


    //region - Getter, Setter -

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HashMap<String, Object> getOptions() {
        return options;
    }

    public void setOptions(HashMap<String, Object> options) {
        this.options = options;
    }

    //endregion

}