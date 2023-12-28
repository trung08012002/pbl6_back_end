package com.example.supportlearningjp.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthotityUtils {
    private  AuthotityUtils(){}
    public static Collection<? extends GrantedAuthority> commaSeperatedStringToAuthorityList(String authorities){
     List<GrantedAuthority>  authorityList=new ArrayList<>();
     String[] authorityStringList= authorities.split(",");
     for (String authority : authorityStringList)
     {
         authorityList.add(new SimpleGrantedAuthority(authority));
     }
     return  authorityList;
    }
}
