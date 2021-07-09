package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "order_details")
public class OrderDetail extends BaseModel {

    //region - Define Fields -
    //private int orderId; //Foreign key - Relationship
    //private int productId; //Foreign key - Relationship

    @NotNull
    private int qty;

    @NotNull
    @Digits(integer = 16, fraction = 2)
    private double amount;

    @NotNull
    @Digits(integer = 16, fraction = 2)
    private double totalAmount;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "orderId") //updatable = false, insertable = false
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId") //updatable = false, insertable = false
    private Product product;
    //endregion


    //region - Getter, Setter -
    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    //endregion

}