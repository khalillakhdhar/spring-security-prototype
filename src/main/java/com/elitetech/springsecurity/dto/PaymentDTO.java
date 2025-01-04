package com.elitetech.springsecurity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PaymentDTO {
    private Long id;

    @NotNull(message = "Le montant du paiement est obligatoire")
    @Positive(message = "Le montant doit être positif")
    private Double amount;

    @NotNull(message = "La date du paiement est obligatoire")
    private Date date;

    @NotBlank(message = "La méthode de paiement est obligatoire")
    private String method;

    
    private OrderDTO order;
}
