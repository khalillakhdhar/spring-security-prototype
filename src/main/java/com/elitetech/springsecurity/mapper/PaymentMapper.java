package com.elitetech.springsecurity.mapper;

import org.modelmapper.ModelMapper;

import com.elitetech.springsecurity.dto.PaymentDTO;
import com.elitetech.springsecurity.entity.Payment;

public class PaymentMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static PaymentDTO convertToDto(Payment payment) {
        return modelMapper.map(payment, PaymentDTO.class);
    }

    public static Payment convertToEntity(PaymentDTO paymentDTO) {
        return modelMapper.map(paymentDTO, Payment.class);
    }
}
