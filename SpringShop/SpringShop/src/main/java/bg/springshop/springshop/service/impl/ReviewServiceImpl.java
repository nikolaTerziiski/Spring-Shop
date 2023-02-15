package bg.springshop.springshop.service.impl;

import bg.springshop.springshop.model.entity.Product;
import bg.springshop.springshop.model.entity.Review;
import bg.springshop.springshop.model.view.ReviewViewModel;
import bg.springshop.springshop.repository.ProductRepository;
import bg.springshop.springshop.service.ReviewService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ProductRepository productRepository;

    public ReviewServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public List<ReviewViewModel> getAllReviews(Long productId) {
        Product product = this.productRepository.findById(productId).orElse(null);


        return product.getReviews().stream().map(this::mapAsReview).collect(Collectors.toList());

    }
    private ReviewViewModel mapAsReview(Review commentEntity) {
        ReviewViewModel reviewViewModel = new ReviewViewModel();

        reviewViewModel.setId(commentEntity.getId());
        reviewViewModel.setDate(commentEntity.getDate());
        reviewViewModel.setMessage(commentEntity.getMessage());
        reviewViewModel.setAuthorName(commentEntity.getAuthor().getUsername());
        return reviewViewModel;
    }
}
