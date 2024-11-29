package com.nasya.blog.model.request.category;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCategoryRequest {

    @NotNull
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String slug;
}
