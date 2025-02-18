package com.elitetech.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.elitetech.springsecurity.entity.AuthRequest;
import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.service.JwtService;
import com.elitetech.springsecurity.service.UserInfoService;

import java.util.List;
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to Spring Security tutorials !!");
    }

    @PostMapping("/add")
    //@PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo userInfo){
        
        return ResponseEntity.ok(userInfoService.addUser(userInfo));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest){
        try {
<<<<<<< HEAD
            System.out.println("Authenticating user: " + authRequest.getUserName());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
                UserInfo user = userInfoService.getUserByEmail(authRequest.getUserName());
                if (user != null) {
                    // Convertir les rôles en chaînes de texte
                    Set<String> roleNames = user.getRoles()
                            .stream()
                            .map(role -> role.getName()) // Assuming Role has a getName() method
                            .collect(Collectors.toSet());

                    String token = jwtService.generateToken(user.getName(), user.getId(), roleNames);
                    System.out.println("Token generated for user: " + user.getName());
                    return ResponseEntity.ok(token);
                } else {
                    System.out.println("Invalid user request: user not found");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user request");
                }
=======
            Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
            if(authenticate.isAuthenticated()){
                return ResponseEntity.ok(jwtService.generateToken(authRequest.getUserName()));
>>>>>>> parent of 0bba2ec (enhancedmode)
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user request");
        }
    }
  

    @GetMapping("/getUsers")
    @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public ResponseEntity<List<UserInfo>> getAllUsers(){
        return ResponseEntity.ok(userInfoService.getAllUser());
    }

    @GetMapping("/getUsers/{id}")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public ResponseEntity<UserInfo> getUser(@PathVariable Integer id){
        UserInfo user = userInfoService.getUser(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/getUser/{name}")
    @PreAuthorize("hasAuthority('USER_ROLES')")
    public ResponseEntity<UserInfo> getOneUser(@PathVariable String name){
        UserInfo user = userInfoService.getOneUser(name);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }}
