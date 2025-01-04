package com.elitetech.springsecurity.dto;

import com.elitetech.springsecurity.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrderDTO {
    private Long id;

    @NotNull(message = "La date de commande est obligatoire")
    private Date date;

    @NotBlank(message = "Le statut de la commande est obligatoire")
    private String status;

    @NotNull(message = "L'utilisateur de la commande est obligatoire")
    private UserDTO user;

    
    private Set<ProductDTO> products;
}
