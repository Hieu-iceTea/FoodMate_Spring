package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority extends BaseModel implements Serializable {

    //region - Define Fields -
    //private String username; //Foreign key - Relationship

    @NotNull
    @Size(min = 2, max = 128)
    private String authority;
    //endregion


    //region - Relationship -
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "username") //updatable = false, insertable = false
    private User user;
    //endregion


    //region - Getter, Setter -
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //endregion

}