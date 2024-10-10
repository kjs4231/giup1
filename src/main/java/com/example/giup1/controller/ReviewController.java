package com.example.giup1.controller;

import com.example.giup1.dto.RequestDto;
import com.example.giup1.dto.ProductResponseDto;
import com.example.giup1.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<String> addReview(
            @PathVariable Long productId,
            @RequestPart("request") RequestDto requestDto,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        String resultMessage = reviewService.createReview(productId, requestDto, image);
        return ResponseEntity.ok(resultMessage);
    }

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<ProductResponseDto> getAllReview(@PathVariable Long productId) {
        ProductResponseDto reviewResponse = reviewService.getAllReview(productId);
        return ResponseEntity.ok(reviewResponse);
    }

}
