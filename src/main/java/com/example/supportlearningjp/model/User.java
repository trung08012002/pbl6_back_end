package com.example.supportlearningjp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;

import java.util.Date;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;

 @Column(name = "user_name")
 private String username;
 @Column(nullable = false,name = "email")
 private String email;
 @Column(nullable = false,name = "password")
 private String password;
 @Column(name = "date_of_birth")
 private Date dateOfBirth;
 @Column(name="address")
 private String address;

 @Column(name = "facebook_account_id")
 private int facebookAccountId;

 @Column(name = "google_account_id")
 private  int googleAccountId;

 @Column(name = "phone_number")
 private String phoneNumber;
 @ManyToMany
 @JoinTable(
         name="users_roles",
         joinColumns =@JoinColumn(name = "user_id"),
         inverseJoinColumns = @JoinColumn(name = "role_id")
 )
 private List<Role> roles=new ArrayList<>();


}
