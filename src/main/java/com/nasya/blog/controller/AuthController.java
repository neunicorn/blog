package com.nasya.blog.controller;

import com.nasya.blog.model.request.auth.LoginRequest;
import com.nasya.blog.model.response.auth.LoginResponse;
import com.nasya.blog.security.CustomUserDetailService;
import com.nasya.blog.security.JwtProvider;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        if(authentication.isAuthenticated()){
            String token =
                    jwtProvider.generateToken
                            (userDetailService.loadUserByUsername(request.getUsername()));

            return ResponseEntity.ok()
                    .body(LoginResponse.builder()
                            .token(token)
                            .build());
        }

        throw new UsernameNotFoundException("USER_NOT_FOUND");
    }
}
