package com.nasya.blog.model.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetCommentResponse {

    private String name;
    private String email;
    private String body;
    private BigInteger createdAat;
    private Post post;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Post{
        private String title;
        private String slug;
    }
}
