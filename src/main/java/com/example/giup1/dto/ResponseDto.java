package com.example.giup1.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ResponseDto {

    private final Long id;
    private final Long userId;
    private final int score;
    private final String content;
    private final String imageUrl;
    private final LocalDateTime createdAt;

    public ResponseDto(Long id, Long userId, int score, String content, String imageUrl, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.content = content;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }
}
