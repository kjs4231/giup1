package com.example.giup1.dto;

import lombok.Getter;

@Getter
public class RequestDto {

    private final Long userId;
    private final int score;
    private final String content;

    public RequestDto(Long userId, int score, String content) {
        this.userId = userId;
        this.score = score;
        this.content = content;
    }
}
