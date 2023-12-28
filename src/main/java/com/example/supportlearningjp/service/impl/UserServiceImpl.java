package com.example.supportlearningjp.service.impl;


import com.example.supportlearningjp.dto.CustomUserDetails;
import com.example.supportlearningjp.dto.UserDto;

import com.example.supportlearningjp.exception.CustomError;
import com.example.supportlearningjp.model.Role;
import com.example.supportlearningjp.model.User;
import com.example.supportlearningjp.repo.RoleRepository;
import com.example.supportlearningjp.repo.UserRepository;
import com.example.supportlearningjp.service.JWTService;
import com.example.supportlearningjp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;


    @Override
    public Map<String,String> login(String email, String password) {
        Optional<User> optionalUser=userRepository.findByEmail(email);
        if(optionalUser.isEmpty())
        {
            throw new EmptyResultDataAccessException("Invalid email",1);
        }
        User existingUser=optionalUser.get();
        if(!passwordEncoder.matches(password, existingUser.getPassword()))
        {
            throw new BadCredentialsException("Wrong password");
        }

        Map<String,String> tokens=createTokens(existingUser);
       String[] listRoles=  existingUser.getRoles().stream().map(Role::getName).toArray(String[]::new);
        String roles=String.join(",",listRoles);
        tokens.put("role",roles);
        tokens.put("email",existingUser.getEmail());

        return tokens;

    }
    private Map<String,String> createTokens(User user)
    {
        String access_token=jwtService.generateToken(user);
        String refresh_token= jwtService.generateRefreshToken(user);
        Map<String,String> tokens=new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        return  tokens;
    }
    @Override
    public Map<String,String> createUser(UserDto user) {
        User newUser= new  User();
        newUser.setUsername(user.getUserName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setDateOfBirth(user.getDataOfBirth());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setAddress(user.getAddress());
        newUser.setGoogleAccountId(user.getGoogleAccountId());
        newUser.setFacebookAccountId(user.getFacebookAccountId());

          Optional<User> isExisted=   userRepository.findByEmail(user.getEmail());
          if(isExisted.isPresent())
          {
              throw new CustomError("Existed account!");
          }
          Role role=roleRepository.findById(user.getRoleId()).orElseThrow(()-> new EmptyResultDataAccessException("Role not found",1));
          newUser.setRoles(List.of(role));
          if(user.getFacebookAccountId()==0 && user.getGoogleAccountId()==0)
          {
              String password=user.getPassword();
              String encodedPassword=passwordEncoder.encode(password);
              newUser.setPassword(encodedPassword);
          }
        Map<String,String> tokens=createTokens(newUser);
          User createdUser=userRepository.save(newUser);
        String[] listRoles=  createdUser.getRoles().stream().map(Role::getName).toArray(String[]::new);
        String roles=String.join(",",listRoles);
        tokens.put("role",roles);
        tokens.put("email",createdUser.getEmail());
        return  tokens;
    }
}
