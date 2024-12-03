package com.nasya.blog.controller;

import com.nasya.blog.config.Bucket4jConfig;
import com.nasya.blog.exception.ApiException;
import com.nasya.blog.model.request.auth.LoginRequest;
import com.nasya.blog.model.response.auth.LoginResponse;
import com.nasya.blog.security.CustomUserDetailService;
import com.nasya.blog.security.JwtProvider;
import io.github.bucket4j.Bucket;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/public/auth")
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private Bucket4jConfig bucket4jConfig;

//    private final Map<String, Bucket> bucketMap = new ConcurrentHashMap<>();


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){

//        Bucket loginBucket = bucketMap.computeIfAbsent(
//                request.getUsername(), k -> bucket4jConfig.loginBucket()
//        );

        Bucket loginBucket = bucket4jConfig.loginBucket(request.getUsername());

        if(!loginBucket.tryConsume(1)){
            throw new ApiException("TOO MANY REQUEST", HttpStatus.TOO_MANY_REQUESTS);
        }


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
