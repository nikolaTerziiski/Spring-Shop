package bg.springshop.springshop.service;

import bg.springshop.springshop.model.binding.ProductCreateBindingModel;
import bg.springshop.springshop.model.binding.ProductEditBindingModel;
import bg.springshop.springshop.model.entity.Category;
import bg.springshop.springshop.model.entity.enums.CategoryEnum;
import bg.springshop.springshop.model.view.ProductAllViewModel;
import bg.springshop.springshop.model.view.ProductDetailsViewModel;

import java.util.List;

public interface ProductService {
    void addProduct(ProductCreateBindingModel productBindingModel);

    ProductDetailsViewModel createProductForDetails(Long id);

    void incrementProductView(Long id);

    List<ProductAllViewModel> takeAll();

    boolean doesUserCreateProduct(Long id);

    ProductEditBindingModel createProductForEdit(Long id);

    void editProduct(ProductEditBindingModel productEditBindingModel, Long id);
    
    boolean doesProductExist(Long id);

    void deleteProduct(Long id);

    List<ProductAllViewModel> getProductsByCategory(Category category);
}
