package com.elitetech.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitetech.springsecurity.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
