package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseModel {

    //region - Define Fields -
    //private int userId; //Foreign key - Relationship
    //private int restaurantId; //Foreign key - Relationship

    @NotNull
    @Size(min = 2, max = 128)
    private String deliveryAddress;

    @NotNull
    private int paymentType;

    @NotNull
    @Digits(integer = 16, fraction = 2)
    private double totalAmount;

    @NotNull
    private int status;

    @Size(min = 2, max = 128)
    private String reasonReject;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "userId") //updatable = false, insertable = false
    private User user;

    @ManyToOne
    @JoinColumn(name = "restaurantId") //updatable = false, insertable = false
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order") //updatable = false, insertable = false
    private List<OrderDetail> orderDetails;
    //endregion


    //region - Getter, Setter -
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReasonReject() {
        return reasonReject;
    }

    public void setReasonReject(String reasonReject) {
        this.reasonReject = reasonReject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    //endregion

}