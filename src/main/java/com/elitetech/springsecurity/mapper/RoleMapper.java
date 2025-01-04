package com.elitetech.springsecurity.mapper;

import org.modelmapper.ModelMapper;

import com.elitetech.springsecurity.dto.RoleDTO;
import com.elitetech.springsecurity.entity.Role;

public class RoleMapper {
	 private static final ModelMapper modelMapper = new ModelMapper();

	    public static RoleDTO convertToDto(Role role) {
	        return modelMapper.map(role, RoleDTO.class);
	    }

	    public static Role convertToEntity(RoleDTO roleDTO) {
	        return modelMapper.map(roleDTO, Role.class);
	    }
}
