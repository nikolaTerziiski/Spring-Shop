package bg.springshop.springshop.web;

import bg.springshop.springshop.model.view.ReviewViewModel;
import bg.springshop.springshop.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class ReviewRestController {
    private final ReviewService  reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/api/{productId}/reviews")
    public ResponseEntity<List<ReviewViewModel>> getReviews(@PathVariable Long productId,
    Principal principal) {

        return ResponseEntity.ok(reviewService.getAllReviews(productId));
    }
}
