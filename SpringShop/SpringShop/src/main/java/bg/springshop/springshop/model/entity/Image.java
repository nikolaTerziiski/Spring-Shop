package bg.springshop.springshop.model.entity;


import javax.persistence.*;


public class Image extends BaseEntity {
    public String url;
    public Product product;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
