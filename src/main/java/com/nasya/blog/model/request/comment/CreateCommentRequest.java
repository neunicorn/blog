package com.nasya.blog.model.request.comment;

import com.nasya.blog.entity.Post;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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
public class CreateCommentRequest {

    @Size(min = 2, max = 100)
    @NotNull
    private String name;
    @Size(min = 2, max = 100)
    @NotNull
    @Email
    private String email;
    @Size(min = 2)
    @NotNull
    private String body;
    @NotNull
    private Post post;

}
