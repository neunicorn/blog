package com.nasya.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    private Integer id;
    private String title;
    private String body;
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private BigInteger createdAt;
    private BigInteger updatedAt;
}
