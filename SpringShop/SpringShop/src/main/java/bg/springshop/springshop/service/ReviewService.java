package bg.springshop.springshop.service;

import bg.springshop.springshop.model.view.ReviewViewModel;

import java.util.List;

public interface ReviewService {
    List<ReviewViewModel> getAllReviews(Long productId);
}
