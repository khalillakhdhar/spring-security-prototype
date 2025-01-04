package com.elitetech.springsecurity.mapper;

import org.modelmapper.ModelMapper;

import com.elitetech.springsecurity.dto.UserDTO;
import com.elitetech.springsecurity.entity.UserInfo;

public class UserInfoMapper {
	 private static final ModelMapper modelMapper = new ModelMapper();

	    public static UserDTO convertToDto(UserInfo userInfo) {
	        return modelMapper.map(userInfo, UserDTO.class);
	    }

	    public static UserInfo convertToEntity(UserDTO userInfoDTO) {
	        return modelMapper.map(userInfoDTO, UserInfo.class);
	    }
}
