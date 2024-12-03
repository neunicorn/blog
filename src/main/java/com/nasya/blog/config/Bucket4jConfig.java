package com.nasya.blog.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Bucket4jConfig {

    public Bucket loginBucket(){
        Bandwidth limit = Bandwidth.builder()
                .capacity(5)
                .refillIntervally(3L, Duration.ofMinutes(1L))
                .build();
        return Bucket.builder().addLimit(limit).build();
    };
}