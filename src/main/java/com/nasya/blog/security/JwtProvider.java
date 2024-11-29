package com.nasya.blog.security;

import com.nasya.blog.properties.SecretProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {

    @Autowired
    private SecretProperties secretProperties;

    public String generateToken(UserDetails userDetails) {
        Map<String, String> claims = new HashMap<>();
        claims.put("iss", secretProperties.getJwtIss());
        Instant now = Instant.now();
        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(10*60*24)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodeKey = Base64.getDecoder().decode(secretProperties.getJwtSecret());
        return Keys.hmacShaKeyFor(decodeKey);
    }

    public String getUsername(String token){
        Claims claims = getClaim(token);
        return claims.getSubject();
    }

    //validate token isExpired?
    public boolean isExpired(String token){
        Claims claims = getClaim(token);
        return claims.getExpiration().before(Date.from(Instant.now()));
    }

    private Claims getClaim(String token){
        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
