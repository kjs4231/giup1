package com.example.giup1.service;

import com.example.giup1.dto.ProductResponseDto;
import com.example.giup1.dto.RequestDto;
import com.example.giup1.dto.ResponseDto;
import com.example.giup1.entity.Product;
import com.example.giup1.entity.Review;
import com.example.giup1.repository.ProductRepository;
import com.example.giup1.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public String createReview(Long productId, RequestDto requestDto, MultipartFile image) {

        // 점수 검사
        if (requestDto.getScore() < 1 || requestDto.getScore() > 5) {
            throw new IllegalArgumentException("점수 범위 초과.");
        }

        // 상품 조회
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 상품입니다."));

//        // 중복 검사. 이제 이건 필요 없음.
//        if (reviewRepository.existsByProductIdAndUserId(productId, requestDto.getUserId())) {
//            return "이미 작성했습니다.";
//        }

        String imageUrl = uploadImage(image);

        // 엔티티에 저장
        Review review = new Review();
        review.setProduct(product);
        review.setUserId(requestDto.getUserId());
        review.setScore(requestDto.getScore());
        review.setContent(requestDto.getContent());
        review.setImageUrl(imageUrl);
        review.setCreatedAt(LocalDateTime.now());

        // 리뷰수나 평점계산은 리뷰가 등록될때마다 하는게 더 효율적.
        product.addReview(requestDto.getScore());
        reviewRepository.save(review);

        return "등록 완료.";
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getAllReview(Long productId) {

        List<Review> reviews = reviewRepository.findByProductIdOrderByCreatedAtDesc(productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않는 상품입니다."));

        // 이걸 서비스에서 처리하는게 나은지 엔티티에서 한꺼번에 하는게 나은지에 대한 고민...
        // gpt는 조회만 빈번하다면 엔티티에서, 조회와 저장 모두 빈번하다면 서비스에서 처리하는게 더 적합하다고 답변
        float roundedScore = Math.round(product.getScore() * 10) / 10.0f;

        // 전체리뷰를 맵으로 리스트로 만듦
        List<ResponseDto> responseDtos = reviews.stream()
                .map(review -> new ResponseDto(
                        review.getId(),
                        review.getUserId(),
                        review.getScore(),
                        review.getContent(),
                        review.getImageUrl(),
                        review.getCreatedAt()
                )).collect(Collectors.toList());

        return new ProductResponseDto(product.getReviewCount().intValue(), roundedScore, 0, responseDtos);
    }

    //더미 이미지
    private String uploadImage(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            return "/image.png" + file.getOriginalFilename();
        }
        return null;
    }
}
