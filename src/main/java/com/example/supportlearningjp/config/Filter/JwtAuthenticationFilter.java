package com.example.supportlearningjp.config.Filter;

import com.example.supportlearningjp.service.JWTService;
import com.example.supportlearningjp.service.UserService;
import com.example.supportlearningjp.util.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.stream;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private  final JWTService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     if(byPassToken(request))
     {
          filterChain.doFilter(request,response);
          return;
     }

         String jwt= JwtUtils.getJwtFromRequest(request);
         if(StringUtils.hasText(jwt) && jwtService.validateToken(jwt)&& SecurityContextHolder.getContext().getAuthentication()==null){
             String email= jwtService.getEmailFromJwt(jwt);
             UserDetails userDetails=userDetailsService.loadUserByUsername(email);
             SecurityContext securityContext= SecurityContextHolder.createEmptyContext();
//           Collection<SimpleGrantedAuthority> authorities=new ArrayList<>();
//          String[] roles=jwtService.getRolesFromJwt(jwt);
//          stream(roles).forEach(role->authorities.add(new SimpleGrantedAuthority(role))
//          );
             UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
             token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             securityContext.setAuthentication(token);
             SecurityContextHolder.setContext(securityContext);
         }
         filterChain.doFilter(request,response);

    }
    private boolean byPassToken(HttpServletRequest request)
    {
        final List<Pair<String,String>> byPassTokens= Arrays.asList(
                Pair.of("register","POST"),
                Pair.of("login","POST"),
                Pair.of("api/token/refreshToken","GET")
        );
        for (Pair<String,String> bypassToken:byPassTokens)
        {

            if(request.getServletPath().contains(bypassToken.getFirst())&&
                    request.getMethod().equals(bypassToken.getSecond()))
            {
                return true;
            }

        }
        return false;
    }

}