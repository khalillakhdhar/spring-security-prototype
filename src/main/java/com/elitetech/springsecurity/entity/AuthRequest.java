package com.elitetech.springsecurity.entity;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Le nom d'utilisateur est obligatoire")
    private String userName;

    @NotBlank(message = "Le mot de passe est obligatoire")
    private String password;
}

