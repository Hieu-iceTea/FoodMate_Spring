package com.example.FoodMate_Spring.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {

    //region - Define Fields -
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(name = "product_category_id")
    //private Integer productCategoryId;
    //@Column(name = "restaurant_id")
    private Integer restaurantId;

    private String name;
    private String ingredients;
    private double price;
    private String image;
    private String country;
    private String tag;
    private String description;
    private boolean featured;

    //@Column(name = "created_by")
    private String createdBy;
    //@Column(name = "created_at")
    private Date createdAt;
    //@Column(name = "updated_by")
    private String updatedBy;
    //@Column(name = "updated_at")
    private Date updatedAt;
    private int version;
    private boolean deleted;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "productCategoryId") //updatable = false, insertable = false
    private ProductCategory productCategory;
    //endregion


    //region - Getter, Setter -
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCategoryId() {
        //return productCategoryId;

        return productCategory.getId(); //Relationship
    }

    public void setProductCategoryId(Integer productCategoryId) {
        //this.productCategoryId = productCategoryId;

        productCategory.setId(productCategoryId); //Relationship
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
    //endregion
}