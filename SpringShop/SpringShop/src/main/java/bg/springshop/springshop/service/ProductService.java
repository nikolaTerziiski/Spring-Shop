package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.ProductBindingModel;
import bg.springshop.springshop.model.view.ProductDetailsViewModel;

public interface ProductService {
    void addProduct(ProductBindingModel productBindingModel);

    ProductDetailsViewModel createProductForDetails(Long id);
}
