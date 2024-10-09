package com.example.giup1.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ReviewResponseDto {
    private final int totalCount;  // 총 리뷰 수
    private final double score;     // 평균 점수
    private final int cursor;       // 커서 값
    private final List<ResponseDto> reviews;  // 리뷰 목록

    public ReviewResponseDto(int totalCount, double score, int cursor, List<ResponseDto> reviews) {
        this.totalCount = totalCount;
        this.score = score;
        this.cursor = cursor;
        this.reviews = reviews;
    }
}
