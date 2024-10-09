package com.example.giup1.service;

import com.example.giup1.dto.RequestDto;
import com.example.giup1.dto.ResponseDto;
import com.example.giup1.dto.ReviewResponseDto;
import com.example.giup1.entity.Product;
import com.example.giup1.entity.Review;
import com.example.giup1.repository.ProductRepository;
import com.example.giup1.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReviewService(ReviewRepository reviewRepository, ProductRepository productRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public String createReview(Long productId, RequestDto requestDto) {
        // 중복 검사
        if (reviewRepository.existsByProductIdAndUserId(productId, requestDto.getUserId())) {
            return "이미 작성했습니다.";
        }

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 ID입니다."));

        // 엔티티에 저장
        Review review = new Review();
        review.setProduct(product);
        review.setUserId(requestDto.getUserId());
        review.setScore(requestDto.getScore());
        review.setContent(requestDto.getContent());
        review.setCreatedAt(LocalDateTime.now());

        reviewRepository.save(review);

        return "등록 완료.";
    }

    public ReviewResponseDto getAllReview(Long productId) {
        List<Review> reviews = reviewRepository.findByProductIdOrderByCreatedAtDesc(productId);

        // 총 리뷰 수
        int totalCount = reviews.size();

        // 평균 점수 계산
        double score = reviews.stream()
                .mapToInt(Review::getScore)
                .average()
                .orElse(0.0); // 리뷰가 없을 경우 0.0 반환

        // 커서 값 설정 (여기서는 단순히 예시로 6으로 설정)
        int cursor = 6; // 실제 구현에 따라 적절히 수정

        // 리뷰를 ResponseDto로 변환
        List<ResponseDto> responseDtos = reviews.stream()
                .map(review -> new ResponseDto(
                        review.getId(),
                        review.getUserId(),
                        review.getScore(),
                        review.getContent(),
                        review.getImageUrl(),
                        review.getCreatedAt()
                ))
                .collect(Collectors.toList());

        // ReviewResponseDto 생성
        return new ReviewResponseDto(totalCount, score, cursor, responseDtos);
    }
}
