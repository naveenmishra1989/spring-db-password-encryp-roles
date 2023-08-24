package com.example.springinmemorypassword.controller;

import com.example.springinmemorypassword.config.MyUserDetailsService;
import com.example.springinmemorypassword.entity.User;
import com.example.springinmemorypassword.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class WelcomeController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @GetMapping("/")
  public ResponseEntity getMessage(){
    return ResponseEntity.ok("<h1>Welcome</h2>"+myUserDetailsService.userName());
  }

  @GetMapping("/admin")
  public ResponseEntity getMessageAdmin(){
    return ResponseEntity.ok("<h1>Welcome Admin</h2>"+myUserDetailsService.userName());
  }

  @GetMapping("/user")
  public ResponseEntity getMessageUser(){
    return ResponseEntity.ok("<h1>Welcome User</h2>"+myUserDetailsService.userName());
  }

  @GetMapping("/newUser")
  public ResponseEntity createNewUser(@RequestBody User user){
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    System.out.println(user);
    userRepository.save(user);
    return ResponseEntity.ok("<h1>Welcome User</h2>");
  }

}
