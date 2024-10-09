package com.example.giup1.controller;

import com.example.giup1.dto.RequestDto;
import com.example.giup1.dto.ReviewResponseDto;
import com.example.giup1.entity.Review;
import com.example.giup1.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{productId}")
    public ResponseEntity<String> addReview(
            @PathVariable Long productId,
            @ModelAttribute RequestDto requestDto) {

        String resultMessage = reviewService.createReview(productId, requestDto);
        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ReviewResponseDto> getAllReview(@PathVariable Long productId) {
        ReviewResponseDto reviewResponse = reviewService.getAllReview(productId);
        return ResponseEntity.ok(reviewResponse);
    }

}
