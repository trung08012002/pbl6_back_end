package com.example.supportlearningjp.service.impl;


import com.example.supportlearningjp.model.Role;
import com.example.supportlearningjp.model.User;
import com.example.supportlearningjp.service.JWTService;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.StringReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;
import java.util.function.Function;

@Component
@Slf4j
public class JWTServiceImpl implements JWTService {
    private final String JWT_SECRET = "lodaaadsaddddddddddaaaaaaaaadsadaaaaasdasdawsdddddddddddddddddddaeqwewqeqw12123213aaa";
    private final long JWT_EXPIRATION = 604800000L;
    private final long REFRESH_EXPIRATION=JWT_EXPIRATION*4;
    private final String REFRESH_SECRET="adasdasdqweddddddsadsadaddddddeddddddddddddddddddddddddddddddddddcvffassbgtrfedassb";
    @Override
    public String generateToken(User user) {
        Date now = new Date();
        Map<String,Object> claims=new HashMap<>();

        claims.put("email",user.getEmail());
        claims.put("roles",user.getRoles().stream().map((Role::getId)).toList());

        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder().setSubject(user.getEmail())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,JWT_SECRET).compact();
    }
    @Override
    public String generateRefreshToken(User user) {
        Date now = new Date();
        Map<String,Object> claims=new HashMap<>();
        System.out.println(user.getEmail());
        claims.put("email",user.getEmail());
        claims.put("roles",user.getRoles().stream().map((Role::getId)).toList());
        Date expiryDate = new Date(now.getTime() + REFRESH_EXPIRATION);
        return Jwts.builder().setSubject(user.getEmail())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,REFRESH_SECRET).compact();
    }


    private<T> T extractClaim(String token, Function<Claims,T> claimsResolvers, String secret)
    {
        final Claims claims=extractAllClaims(token,secret);
        return claimsResolvers.apply(claims);
    }
    private Claims extractAllClaims(String token,String secret)
    {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }
    @Override
    public String getEmailFromJwt(String token){
        return extractClaim(token,(Claims::getSubject),JWT_SECRET);
    }
    @Override
    public String getEmailFromRefreshToken(String token){
        return extractClaim(token,(claims -> String.valueOf(claims.get("email"))),REFRESH_SECRET);
    }
    @Override
    public String[] getRolesFromJwt(String token) {
        Object[] list= Collections.singletonList(extractAllClaims(token,JWT_SECRET).get("roles")).toArray();
        return Arrays.copyOf(list,list.length,String[].class);

    }

    @Override
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
