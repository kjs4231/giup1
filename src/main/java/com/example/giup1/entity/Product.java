package com.example.giup1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long reviewCount = 0L;

    @Column(nullable = false)
    private Float score = 0.0f;

    public void addReview(int newScore) {
        this.reviewCount++;
        this.score = ((this.score * (reviewCount - 1)) + newScore) / reviewCount;
    }

    public void removeReview(int reviewScore) {
        if (this.reviewCount > 0) {
            this.reviewCount--;
            if (this.reviewCount == 0) {
                this.score = 0.0f;
            } else {
                this.score = ((this.score * (reviewCount + 1)) - reviewScore) / reviewCount;
            }
        }
    }


}
