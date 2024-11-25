package com.santosh.booknestapp.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.Column;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    private final String secretKey = "Y2FkZTJlYmYwOGZkZGZlNTYwMGM1YTcwMjc4YjFhMzEwNzJjNzFmMzFlNzc4OTc5ZjVjYzg2YjNjN2Fk";
    public String generateToken(String userName){
        Map<String,Object> claims=new HashMap<>();

//        Jwts Builder to generate key Imp
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
//        Jwts ParseBuilder to parse and extract userName with Key Imp
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(UserDetails userDetails, String token) {
        String userName=userDetails.getUsername();
        String userNameFromToken=extractUserName(token);
        return userName.equals(userNameFromToken);
    }
}
