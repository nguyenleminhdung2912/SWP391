package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class JWTService {
    private static final String SECRET = "!@#$FDGSDFGSGSGSGSHSHSHSSHGFFDSGSFGSSGHSDFSDFSFSFSFSDFSFSFSFDSADSA";
    private final long EXPIRATION = 1 * 24 * 60 * 60 * 1000;
    public String generateToken(String email) {
        Date now = new Date(); // get current time
        Date expirationDate = new Date(now.getTime() + EXPIRATION);

        String token = Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        return token;
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
        try{
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before((new Date()));
    }

    public Boolean validateToken(String token, AccountEntity userDetails){
        final String userName= extractEmail(token);
        return (userName.equals(userDetails.getEmail()) && !isTokenExpired(token));
    }
}
