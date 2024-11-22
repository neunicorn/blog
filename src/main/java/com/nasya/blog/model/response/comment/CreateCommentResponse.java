package com.nasya.blog.model.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCommentResponse {

    private Integer id;
    private String name;
    private String email;
    private String body;

    private Post post;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    private static class Post{
        private String title;
        private String slug;
    }
}
