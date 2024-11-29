package com.nasya.blog.model.response.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCategoryResponse {
    private String name;
    private BigInteger createdAt;
}
