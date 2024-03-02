package com.swp391.webapp.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JWTService {
    private static final String SECRET = "!@#$FDGSDFGSGSGSGSHSHSHSSHGFFDSGSFGSSGHSDFSDFSFSFSFSDFSFSFSFDSADSA";

    public String generateToken(String email) {
        Map<String, Objects> claims = new HashMap<>();
        Date now = new Date();
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(now.getTime()))
                .setExpiration(new Date(now.getTime() + 1000 * 60 * 60 * 24 * 30))
                .signWith(SignatureAlgorithm.HS256, SECRET).compact();
    }


    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before((new Date()));
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName= extractEmail(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}