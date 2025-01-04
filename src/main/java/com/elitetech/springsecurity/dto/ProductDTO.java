package com.elitetech.springsecurity.dto;

import java.util.HashSet;
import java.util.Set;

import com.elitetech.springsecurity.entity.Commande;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductDTO {
    private Long id;

    @NotBlank(message = "Le nom du produit est obligatoire")
    private String name;

    @NotBlank(message = "La description est obligatoire")
    private String description;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    private Double price;

    @NotNull(message = "Le stock est obligatoire")
    @Positive(message = "Le stock doit être supérieur à zéro")
    private Integer stock;
    private Set<OrderDTO> orders = new HashSet<>();
}
