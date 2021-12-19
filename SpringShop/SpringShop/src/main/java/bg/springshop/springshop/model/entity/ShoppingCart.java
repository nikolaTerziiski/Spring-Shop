package bg.springshop.springshop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shoppingCarts")
public class ShoppingCart extends BaseEntity{

    public List<Product> products;

    @ManyToMany
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
