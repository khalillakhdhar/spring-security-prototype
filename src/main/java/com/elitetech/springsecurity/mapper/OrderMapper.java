package com.elitetech.springsecurity.mapper;

import org.modelmapper.ModelMapper;

import com.elitetech.springsecurity.dto.OrderDTO;
import com.elitetech.springsecurity.entity.Commande;

public class OrderMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static OrderDTO convertToDto(Commande order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public static Commande convertToEntity(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Commande.class);
    }
}
