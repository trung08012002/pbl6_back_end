package com.example.supportlearningjp.fillter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.web.csrf.CsrfToken;
import java.io.IOException;

public class CrsfCookieFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       CsrfToken csrfToken=(CsrfToken) request.getAttribute(CsrfToken.class.getName());
       if(null!=csrfToken.getHeaderName())
       {
           response.setHeader(csrfToken.getHeaderName(),csrfToken.getToken());
       }
       filterChain.doFilter(request,response);
    }
}
