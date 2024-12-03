package com.nasya.blog.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Bucket4jConfig {

    private final ProxyManager proxyManager;

    public Bucket4jConfig(ProxyManager proxyManager) {
        this.proxyManager = proxyManager;
    }



    //bucket token di simpan di redis (database)
    public Bucket loginBucket(String username){
        Bandwidth limit = Bandwidth.builder()
                .capacity(5)
                .refillIntervally(3L, Duration.ofMinutes(1L))
                .build();
        BucketConfiguration configuration = BucketConfiguration.builder().addLimit(limit).build();

        return proxyManager.builder().build(username, ()-> configuration);
    }

    //bucket token disimpan di memori aplikasi, jika aplikasi di restart maka bucket ikut terestart
    public Bucket loginBucket(){
        Bandwidth limit = Bandwidth.builder()
                .capacity(5)
                .refillIntervally(3L, Duration.ofMinutes(1L))
                .build();
        return Bucket.builder().addLimit(limit).build();
    };
}