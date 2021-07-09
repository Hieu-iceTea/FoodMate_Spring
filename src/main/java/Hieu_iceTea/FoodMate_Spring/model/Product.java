package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "products")
public class Product extends BaseModel {

    //region - Define Fields -
    //private int productCategoryId; //Foreign key - Relationship
    //private int restaurantId; //Foreign key - Relationship

    @NotNull
    @Size(min = 2, max = 128)
    private String name;

    @NotNull
    @Size(min = 2, max = 256)
    private String ingredients;

    @NotNull
    @Digits(integer = 16, fraction = 2)
    @Min(1)
    private double price;

    @Size(max = 128)
    private String image;

    @Size(min = 2, max = 32)
    private String country;

    @Size(min = 2, max = 128)
    private String tag;

    private String description;

    @NotNull
    private boolean featured;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "productCategoryId") //updatable = false, insertable = false
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "restaurantId") //updatable = false, insertable = false
    private Restaurant restaurant;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
    //endregion


    //region - Getter, Setter -
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFeatured() {
        return featured;
    }

    public void setFeatured(boolean featured) {
        this.featured = featured;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
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