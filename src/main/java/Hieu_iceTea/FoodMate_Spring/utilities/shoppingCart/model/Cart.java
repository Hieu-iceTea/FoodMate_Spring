package Hieu_iceTea.FoodMate_Spring.utilities.shoppingCart.model;


import java.util.HashMap;

public class Cart {

    //region - Define Fields -
    private String rowId; //Primary key (Khóa chính của đối tượng này)

    private int id; //product.id
    private String name; //product.name
    private double weight; //product.weight
    //private Product product;

    private int qty;
    private double price; //Giá sản phẩm thời điểm người dùng thêm vào giỏ hàng

    private HashMap<String, Object> options; //Các thông tin tùy chọn
    //endregion


    //region - Constructor -
    public Cart(int id, String name, double weight, int qty, double price, HashMap<String, Object> options) {
        this.rowId = "cartRowId_" + id;

        this.id = id;
        this.name = name;
        this.weight = weight;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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