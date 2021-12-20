package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.binding.ProductBindingModel;
import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.view.ProductAllViewModel;
import bg.springshop.springshop.model.view.ProductDetailsViewModel;
import bg.springshop.springshop.repository.ProductRepository;
import bg.springshop.springshop.service.CategoryService;
import bg.springshop.springshop.service.ProductService;
import bg.springshop.springshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    public ProductServiceImpl(ModelMapper modelMapper,
                              ProductRepository productRepository,
                              CategoryService categoryService,
                              UserService userService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void addProduct(ProductBindingModel productBindingModel) {

        Product product = modelMapper.map(productBindingModel, Product.class);

        product.setCategory(this.categoryService.findCategory(productBindingModel.getCategory()));
        product.setCreator(this.userService.getCurrentLoggedInUser());
        product.setViewCount(0);
        product.setImage(productBindingModel.getImage());
        product.setAddedOn(LocalDateTime.now());

        this.productRepository.save(product);
    }

    @Override
    public ProductDetailsViewModel createProductForDetails(Long id) {
        Product product =  this.productRepository.findById(id).orElse(null);

        if(product == null){
            return null;
        }
        return this.modelMapper.map(product, ProductDetailsViewModel.class);
    }

    @Override
    public void incrementProductView(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);

        product.setViewCount(product.getViewCount() + 1);

        this.productRepository.save(product);
    }

    @Override
    public List<ProductAllViewModel> takeAll() {

        List<Product> products = this.productRepository.findAll();

        List<ProductAllViewModel> products2 = products
            .stream().map(e -> {
                ProductAllViewModel productAllViewModel = modelMapper.map(e, ProductAllViewModel.class);
                return productAllViewModel;
            }).collect(Collectors.toList());

        return products2;
    }
}
