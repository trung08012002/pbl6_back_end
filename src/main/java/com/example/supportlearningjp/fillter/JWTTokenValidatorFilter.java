package com.example.supportlearningjp.fillter;

import com.example.supportlearningjp.constants.SecurityConstants;
import com.example.supportlearningjp.util.AuthotityUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt=request.getHeader(SecurityConstants.JWT_HEADER);
        if(jwt!=null)
        {
            try{
                SecretKey key= Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
                Claims claims=Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
                String username=String.valueOf(claims.get("username"));
                String authorities=String.valueOf(claims.get("authorities"));
                Authentication auth=new UsernamePasswordAuthenticationToken(username,null, AuthotityUtils.commaSeperatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            catch (Exception e)
            {
                throw  new BadCredentialsException("Invalid Token received");
            }
        }
        filterChain.doFilter(request,response);
    }
}
