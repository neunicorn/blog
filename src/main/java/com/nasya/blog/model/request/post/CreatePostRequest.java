package com.nasya.blog.model.request.post;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class CreatePostRequest {

    @NotNull
    @Size(min=2, message = "Size minimal 2")
    private String title;
    @NotNull
    @Size(min=10, message = "Size minimal 10")
    private String body;
    @NotNull
    @Size(min=2, message = "Size minimal 2")
    private String slug;

    private Integer categoryId;

}
