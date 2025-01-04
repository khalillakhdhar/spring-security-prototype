package com.elitetech.springsecurity.service.interfaces;


import com.elitetech.springsecurity.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO processPayment(Long orderId, PaymentDTO paymentDTO);

    PaymentDTO updatePayment(Long id, PaymentDTO paymentDTO);

    void deletePayment(Long id);

    PaymentDTO getPaymentById(Long id);

    List<PaymentDTO> getAllPayments();
}
