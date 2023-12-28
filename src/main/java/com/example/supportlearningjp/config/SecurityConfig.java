package com.example.supportlearningjp.config;

import com.example.supportlearningjp.config.Filter.JwtAuthenticationFilter;
import com.example.supportlearningjp.dto.CustomUserDetails;
import com.example.supportlearningjp.model.User;
import com.example.supportlearningjp.repo.UserRepository;


import com.example.supportlearningjp.service.JWTService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {


    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final   JwtAuthenticationFilter jwtAuthenticationFilter;


@Autowired
private  final AuthenticationConfiguration authConfiguration;
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
 public UserDetailsService userDetailsService()
 {
     return (username)->{
         Optional<User> user = userRepository.findByEmail(username);
         if (user.isEmpty()) {
             throw new UsernameNotFoundException(username);
         }
         return new CustomUserDetails(user.get());
     };
 }

    @Bean
 CorsConfigurationSource corsConfigurationSource(){
     CorsConfiguration corsConfiguration=new CorsConfiguration();
     corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
     corsConfiguration.setAllowedMethods(List.of("*"));
     corsConfiguration.setAllowCredentials(true);
     corsConfiguration.setAllowedHeaders(List.of("*"));
     corsConfiguration.setMaxAge(3600L);
     UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**",corsConfiguration);
     return source;
 }


 @Bean
 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     http.csrf(crsf-> {
      try {
       crsf.disable()
               .cors(Customizer.withDefaults())  //by default use a bean by the name of corsConfiguration
               .httpBasic(Customizer.withDefaults());
      } catch (Exception e) {
       throw new RuntimeException(e);
      }
     }).sessionManagement(
             manager->manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     ).authenticationProvider(authenticationProvider())
      .addFilterBefore(
             jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class
      )

     ;

     return http.build();
 }
    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(); }
@Bean
public AuthenticationProvider authenticationProvider()
{
    DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return  authenticationProvider;
}

}
