package com.example.giup1.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ProductResponseDto {
    private final int totalCount;
    private final Float score;
    private final int cursor;
    private final List<ResponseDto> reviews;

    public ProductResponseDto(int totalCount, Float score, int cursor, List<ResponseDto> reviews) {
        this.totalCount = totalCount;
        this.score = score;
        this.cursor = cursor;
        this.reviews = reviews;
    }
}
