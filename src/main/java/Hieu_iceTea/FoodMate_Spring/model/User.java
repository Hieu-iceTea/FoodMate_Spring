package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseModel implements Serializable {

    //region - Define Fields -
    //private int restaurantId; //Foreign key - Relationship


    // - - - - -
    @NotNull
    @Size(min = 2, max = 64)
    private String username;

    @NotNull
    @Email
    @Size(min = 2, max = 64)
    private String email;

    @NotNull
    @Size(min = 2, max = 128)
    private String password;

    @NotNull
    private int level;


    // - - - - -
    private Date email_verified_at;
    private String verification_code;
    private String reset_password_code;
    private String remember_token;


    // - - - - -
    @Size(max = 128)
    private String image;

    private Boolean gender;

    @Size(min = 2, max = 64)
    private String first_name;

    @Size(min = 2, max = 64)
    private String last_name;

    @Size(min = 2, max = 16)
    private String phone;

    @Size(min = 2, max = 128)
    private String address;


    // - - - - -
    @NotNull
    private Boolean enabled;
    //endregion


    //region - Relationship -
    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "restaurantId") //updatable = false, insertable = false
    private Restaurant restaurant;
    //endregion


    //region - Getter, Setter -
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Date getEmail_verified_at() {
        return email_verified_at;
    }

    public void setEmail_verified_at(Date email_verified_at) {
        this.email_verified_at = email_verified_at;
    }

    public String getVerification_code() {
        return verification_code;
    }

    public void setVerification_code(String verification_code) {
        this.verification_code = verification_code;
    }

    public String getReset_password_code() {
        return reset_password_code;
    }

    public void setReset_password_code(String reset_password_code) {
        this.reset_password_code = reset_password_code;
    }

    public String getRemember_token() {
        return remember_token;
    }

    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    //endregion


    //region - Method Extend -
    public String getAuthoritiesString() {
        if (authorities.isEmpty()) {
            return "";
        }

        List<String> arrAuthorities = new ArrayList<>();

        for (Authority authority : authorities) {
            arrAuthorities.add(authority.getAuthority().replace("ROLE_", ""));
        }

        return arrAuthorities.toString().replace("[", "").replace("]", "");
    }
    //endregion

}