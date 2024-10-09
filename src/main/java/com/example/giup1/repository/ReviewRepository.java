package com.example.giup1.repository;

import com.example.giup1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 유저가 이미 리뷰를 작성했는지 확인
    boolean existsByProductIdAndUserId(Long productId, Long userId);

    List<Review> findByProductIdOrderByCreatedAtDesc(Long productId);
}
