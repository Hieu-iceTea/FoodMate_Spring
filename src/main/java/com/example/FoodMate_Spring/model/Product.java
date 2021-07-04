package com.example.FoodMate_Spring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product extends BaseModel {

    //region - Define Fields -
    //private Integer productCategoryId; //Foreign key - Relationship
    //private Integer restaurantId; //Foreign key - Relationship

    private String name;
    private String ingredients;
    private double price;
    private String image;
    private String country;
    private String tag;
    private String description;
    private boolean featured;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "productCategoryId") //updatable = false, insertable = false
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "restaurantId") //updatable = false, insertable = false
    private Restaurant restaurant;
    //endregion


    //region - Getter, Setter -
    public Integer getProductCategoryId() {
        //return productCategoryId;

        return productCategory.getId(); //Relationship
    }

    public void setProductCategoryId(Integer productCategoryId) {
        //this.productCategoryId = productCategoryId;

        productCategory.setId(productCategoryId); //Relationship
    }

    public Integer getRestaurantId() {
        //return restaurantId;

        return restaurant.getId(); //Relationship
    }

    public void setRestaurantId(Integer restaurantId) {
        //this.restaurantId = restaurantId;

        restaurant.setId(restaurantId); //Relationship
    }

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
    //endregion

}