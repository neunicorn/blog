package com.nasya.blog.model.request.post;

import jakarta.validation.Valid;
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
public class UpdatePostRequest {

    @Size(min = 1, max = 100)
    private String title;

    @Size(min=2)
    private String body;

    @Size(min=2)
    private String slug;
}
