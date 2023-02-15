package bg.springshop.springshop.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shoppingCarts")
public class ShoppingCart extends BaseEntity{

    public List<Product> products;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "shopping_carts_products",joinColumns = @JoinColumn(name = "shopping_cart_id"))
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
