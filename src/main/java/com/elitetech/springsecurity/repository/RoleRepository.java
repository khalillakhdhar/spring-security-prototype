package com.elitetech.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elitetech.springsecurity.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
