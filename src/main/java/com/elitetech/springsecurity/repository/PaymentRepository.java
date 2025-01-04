package com.elitetech.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitetech.springsecurity.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
