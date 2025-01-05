
package com.elitetech.springsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.elitetech.springsecurity.entity.AuthRequest;
import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.service.JwtService;
import com.elitetech.springsecurity.service.UserInfoService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to Spring Security tutorials !!");
    }

    @PostMapping("/add")
   // @PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public ResponseEntity<UserInfo> addUser(@RequestBody UserInfo userInfo) {
        return ResponseEntity.ok(userInfoService.addUser(userInfo));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) {
        try {
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

                    String token = jwtService.generateToken(user.getEmail(), user.getId(), roleNames);
                    System.out.println("Token generated for user: " + user.getEmail());
                    return ResponseEntity.ok(token);
                } else {
                    System.out.println("Invalid user request: user not found");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid user request");
                }
            } else {
                System.out.println("Invalid credentials provided");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (UsernameNotFoundException e) {
            System.out.println("Authentication failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }


    @PostMapping("/refresh-token")
    public ResponseEntity<String> refreshToken(@RequestHeader("Authorization") String token) {
        try {
            // Supprime le préfixe "Bearer " si présent
            token = token.startsWith("Bearer") ? token.substring(7) : token;

            String refreshedToken = jwtService.refreshToken(token);
            return ResponseEntity.ok(refreshedToken);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot refresh expired token");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/getUsers")
    //@PreAuthorize("hasAuthority('ADMIN_ROLES')")
    public ResponseEntity<List<UserInfo>> getAllUsers() {
        return ResponseEntity.ok(userInfoService.getAllUser());
    }

    @GetMapping("/getUsers/{id}")
   // @PreAuthorize("hasAuthority('USER_ROLES')")
    public ResponseEntity<UserInfo> getUser(@PathVariable Integer id) {
        UserInfo user = userInfoService.getUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/getUser/{name}")
   // @PreAuthorize("hasAuthority('USER_ROLES')")
    public ResponseEntity<UserInfo> getOneUser(@PathVariable String name) {
        UserInfo user = userInfoService.getOneUser(name);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
