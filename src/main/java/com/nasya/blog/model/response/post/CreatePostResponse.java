package com.nasya.blog.model.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePostResponse {

    private String title;
    private String body;
    private String slug;

    private BigInteger createdAt;
    private Integer commentCount;
}
