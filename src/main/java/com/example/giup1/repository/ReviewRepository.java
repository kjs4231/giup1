package com.example.giup1.repository;

import com.example.giup1.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByProductIdAndIdLessThanOrderByIdDesc(Long productId, Long cursor, Pageable pageable);
    List<Review> findByProductIdOrderByIdDesc(Long productId, Pageable pageable);
}
