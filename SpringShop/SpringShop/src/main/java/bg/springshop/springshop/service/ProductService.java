package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.ProductBindingModel;
import bg.springshop.springshop.model.view.ProductAllViewModel;
import bg.springshop.springshop.model.view.ProductDetailsViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductBindingModel productBindingModel);

    ProductDetailsViewModel createProductForDetails(Long id);

    void incrementProductView(Long id);

    List<ProductAllViewModel> takeAll();
}
