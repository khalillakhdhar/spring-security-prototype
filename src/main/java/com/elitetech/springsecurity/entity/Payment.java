package com.elitetech.springsecurity.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "order"})
public class Payment {
    @Id
    private long id;

    private Double amount;
    private LocalDateTime date;

    private String method;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @JsonIgnoreProperties("payment")
    private Commande order;

    @PrePersist
    public void init() {
        date = LocalDateTime.now();
    }
}


