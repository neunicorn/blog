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
public class UpdateBySlugPostResponse {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private BigInteger updatedAt;
}
