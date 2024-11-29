package com.nasya.blog.model.request.category;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class CreateCategoryRequest {

    @NotNull
    private String name;

    @NotNull
    private String slug;
}
