package com.example.supportlearningjp.service;



import com.example.supportlearningjp.dto.UserDto;
import com.example.supportlearningjp.model.User;

import java.util.Map;


public interface UserService  {
    Map<String,String> login(String email, String password);
    Map<String,String>  createUser(UserDto signUpRequest);

}
