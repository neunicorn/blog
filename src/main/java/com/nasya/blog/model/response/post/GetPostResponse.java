package com.nasya.blog.model.response.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPostResponse {

    private Integer id;
    private String title;
    private String body;
    private String slug;
    private BigInteger publishedAt;
    private Integer commentCount;
    private Category category;
    private boolean published;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Category{
        private String name;
        private String slug;
    }
}
