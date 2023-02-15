package bg.springshop.springshop.model.view;

import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderProfileViewModel {
    private List<Product> products;
    private LocalDateTime created;
    private BigDecimal price;

    public OrderProfileViewModel(List<Product> products, LocalDateTime created, BigDecimal price) {
        this.products = products;
        this.created = created;
        this.price = price;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
