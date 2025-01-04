package com.elitetech.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitetech.springsecurity.entity.Commande;

public interface OrderRepository extends JpaRepository<Commande, Long> {

}
