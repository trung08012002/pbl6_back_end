package com.example.supportlearningjp.controller;

import com.example.supportlearningjp.dto.CustomUserDetails;
import com.example.supportlearningjp.dto.UserDto;
import com.example.supportlearningjp.dto.type.FormAuth;
import com.example.supportlearningjp.dto.type.FormToken;
import com.example.supportlearningjp.model.User;
import com.example.supportlearningjp.repo.UserRepository;
import com.example.supportlearningjp.service.JWTService;
import com.example.supportlearningjp.service.UserService;
import com.example.supportlearningjp.util.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@RestController()
@RequestMapping("/api")
public class UserController {

    @Autowired
    JWTService jwtService;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    UserService userService;

    @PostMapping("/token/refreshToken")
    public ResponseEntity<?> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken= JwtUtils.getJwtFromRequest(request);
        if(refreshToken==null)
        {
            return  ResponseEntity.unprocessableEntity().body("Refresh token is missing");
        }
        try{
            String email = jwtService.getEmailFromRefreshToken(refreshToken);
            CustomUserDetails customUserDetails= (CustomUserDetails) userDetailsService.loadUserByUsername(email);
            User user=customUserDetails.getUser();
            String newAccessToken=jwtService.generateToken(user);
            Map<String,String> tokens=new HashMap<>();
            tokens.put("access_token",newAccessToken);
            return ResponseEntity.ok(tokens);
        }catch (Exception exception){
           log.error(exception.getMessage());
           response.setHeader("error",exception.getMessage());
           response.setStatus(FORBIDDEN.value());
           Map<String,String> error=new HashMap<>();
           error.put("error_message",exception.getMessage());
           response.setContentType(APPLICATION_JSON_VALUE);
           return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }
    @PostMapping("/login")
    ResponseEntity<?> login(@RequestBody FormAuth formAuth)
    {
        try{
            Map<String,String> result= userService.login(formAuth.getEmail(),formAuth.getPassword());
            return ResponseEntity.ok(result);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }

    }
    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody FormAuth formAuth){
        try{
            UserDto userDto=new UserDto();
            userDto.setEmail(formAuth.getEmail());
            userDto.setPassword(formAuth.getPassword());
            userDto.setRoleId(0);
            userDto.setFacebookAccountId(0);
            userDto.setGoogleAccountId(0);
            Map<String,String> result= userService.createUser(userDto);
            return ResponseEntity.ok(result);
        }
        catch (Exception exception)
        {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }
}
