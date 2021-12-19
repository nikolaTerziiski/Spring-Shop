package bg.springshop.springshop.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image extends BaseEntity {
    public String url;
    public Product product;

    @Column(nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToOne
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
