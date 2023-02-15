package bg.springshop.springshop.model.view;

import bg.springshop.springshop.model.entity.Order;
import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.Role;
import bg.springshop.springshop.model.entity.ShoppingCart;

import java.util.List;
import java.util.Set;

public class UserProfileViewModel {
    private String username;
    private String email;
    private String password;
    private Set<Role> roles;
    private List<Product> products;
    private ShoppingCart shoppingCart;
    private List<Order> orders;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
