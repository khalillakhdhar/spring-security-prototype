package com.elitetech.springsecurity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.elitetech.springsecurity.entity.Role;
import com.elitetech.springsecurity.entity.UserInfo;
import com.elitetech.springsecurity.repository.RoleRepository;
import com.elitetech.springsecurity.repository.UserInfoRepository;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service

public class UserInfoService implements UserDetailsService {
    @Autowired
    private  UserInfoRepository userInfoRepository;
    @Autowired
    private RoleRepository roleRepository; 
    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByEmail(username);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public UserInfo getUserByEmail(String email) {
        return userInfoRepository.findByEmail(email).orElse(null);
    }

    public UserInfo getOneUser(String name) {
        return userInfoRepository.findByName(name).orElse(null);
    }


public UserInfo addUser(UserInfo userInfo) {
    // Encoder le mot de passe
    userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));

    // Assurez-vous que chaque rôle est attaché à l'EntityManager
    Set<Role> managedRoles = new HashSet<>();
    for (Role role : userInfo.getRoles()) {
        Role managedRole = roleRepository.findById(role.getId())
                                         .orElseThrow(() -> new RuntimeException("Role not found: " + role.getId()));
        managedRoles.add(managedRole);
    }
    userInfo.setRoles(managedRoles);

    // Sauvegarder l'utilisateur
    return userInfoRepository.save(userInfo);
}

    public List<UserInfo> getAllUser() {
        return userInfoRepository.findAll();
    }

    public UserInfo getUser(long id) {
        return userInfoRepository.findById(id).get();
    }
}

