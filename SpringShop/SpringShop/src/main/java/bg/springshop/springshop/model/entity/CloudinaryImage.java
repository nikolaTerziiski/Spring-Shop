package bg.springshop.springshop.model.entity;

public class CloudinaryImage {

    private String url;
    private Product product;

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
