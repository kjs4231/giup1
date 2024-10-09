package com.example.giup1.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class RequestDto {

    private final Long userId;
    private final int score;
    private final String content;
    private final MultipartFile image;

    public RequestDto(Long userId, int score, String content, MultipartFile image) {
        this.userId = userId;
        this.score = score;
        this.content = content;
        this.image = image;
    }
}
