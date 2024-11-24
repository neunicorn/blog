package com.nasya.blog.model.request.auth;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Valid
public class LoginRequest {

    @NotNull
    private String username;
    @NotNull
    private String password;
}
