package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "product_categories")
public class ProductCategory extends BaseModel {

    //region - Define Fields -
    @NotNull
    @Size(min = 2, max = 64)
    private String name;

    @NotNull
    @Size(max = 128)
    private String image;

    private boolean active;
    //endregion


    //region - Relationship -
    @OneToMany(mappedBy = "productCategory")
    private List<Product> products;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    //endregion

}