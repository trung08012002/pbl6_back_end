package com.example.supportlearningjp.config;

import com.example.supportlearningjp.model.Role;
import com.example.supportlearningjp.model.User;
import com.example.supportlearningjp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName,password=null;
        List<GrantedAuthority> authorities=null;
        Optional<User> user=userRepository.findByUsername(username);
        if(user.isPresent())
        {
            userName=user.get().getEmail();
            password=user.get().getPassword();
            authorities=new ArrayList<>();
            for (Role role: user.get().getRoles())
            {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            }
        }
        else{
            throw new UsernameNotFoundException("User details not found for the user:"+username);
        }
        return new org.springframework.security.core.userdetails.User(username,password,authorities);
    }
}
