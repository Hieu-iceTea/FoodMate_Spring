package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseModel {

    //region - Define Fields -
    //private int userId; //Foreign key - Relationship

    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    @NotNull
    @Email
    @Size(min = 2, max = 128)
    private String email;

    @NotNull
    private String message;

    @NotNull
    private int rating;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "userId") //updatable = false, insertable = false
    private User user;
    //endregion


    //region - Getter, Setter -
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //endregion

}