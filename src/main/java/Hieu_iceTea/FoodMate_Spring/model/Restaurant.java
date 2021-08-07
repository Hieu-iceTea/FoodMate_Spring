package Hieu_iceTea.FoodMate_Spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "restaurants")
//@JsonIgnoreProperties({"hibernateLazyInitial", "handler"})
public class Restaurant extends BaseModel {

    //region - Define Fields -
    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    @Size(max = 128)
    private String image;

    @Size(min = 2, max = 64)
    private String address;

    private String description;
    //endregion


    //region - Relationship -
    @OneToMany(mappedBy = "restaurant")
    private List<Product> products;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<User> users;
    //endregion


    //region - Getter, Setter -
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    //endregion

}