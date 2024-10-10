package com.example.giup1.repository;

import com.example.giup1.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰 중복 검사
    boolean existsByProductIdAndUserId(Long productId, Long userId);

    // 정렬해서 조회
    List<Review> findByProductIdOrderByCreatedAtDesc(Long productId);
}
