package com.example.employeemanagementSystem.customizations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration.ms}")
    private long expirationms;

    //for creating key for server password
    private SecretKey getSigning(){
        byte[] keyBytes= Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    //generationg tokens for loggenIn user
    public String generateToken(UserDetails userDetails){
        Date now=new Date();
        Date expiry=new Date(now.getTime()+expirationms); //24hours
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("role",userDetails.getAuthorities().iterator().next()
                        .getAuthority())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(getSigning(), SignatureAlgorithm.HS256)
                .compact();
    }
    //extractusername
    public String extractUserName(String token){
        return parseClaims(token).getSubject(); //claimsobj.getsubject
    }
    private Claims parseClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigning())
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
    //validation of token and expiration
    public boolean isTokenValid(String token,UserDetails userDetails ){
        String username=userDetails.getUsername();
        return username.equals(userDetails.getUsername()) &&
                !isTokenExpired(token);
    }
    public boolean isTokenExpired(String token){
        return parseClaims(token).getExpiration().before(new Date());
    }

}
