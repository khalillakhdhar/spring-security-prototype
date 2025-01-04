package com.elitetech.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elitetech.springsecurity.dto.OrderDTO;
import com.elitetech.springsecurity.dto.PaymentDTO;

import com.elitetech.springsecurity.entity.Payment;

import com.elitetech.springsecurity.mapper.OrderMapper;
import com.elitetech.springsecurity.mapper.PaymentMapper;
import com.elitetech.springsecurity.repository.OrderRepository;
import com.elitetech.springsecurity.repository.PaymentRepository;
import com.elitetech.springsecurity.service.interfaces.PaymentService;


import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{
	private final OrderRepository orderRepository;
	private final PaymentRepository paymentRepository;
	@Override
	public PaymentDTO processPayment(Long orderId, PaymentDTO paymentDTO) {
		// TODO Auto-generated method stub
		PaymentDTO payment=PaymentMapper.convertToDto(paymentRepository.findById(orderId).get());
		OrderDTO ord=OrderMapper.convertToDto(orderRepository.findById(orderId).get());
		payment.setOrder(ord);
		
		return PaymentMapper.convertToDto(paymentRepository.save(PaymentMapper.convertToEntity(payment)));
	}

	

	@Override
	public void deletePayment(Long id) {
		// TODO Auto-generated method stub
		paymentRepository.deleteById(id);
		
	}

	@Override
	public PaymentDTO getPaymentById(Long id) {
		// TODO Auto-generated method stub
		return PaymentMapper.convertToDto(paymentRepository.findById(id).orElse(null));
	}

	@Override
	public List<PaymentDTO> getAllPayments() {
		// TODO Auto-generated method stub
		List<Payment> payments=paymentRepository.findAll();
		List<PaymentDTO> managedPayments=new ArrayList<PaymentDTO>();
		payments.forEach(pa->{
			managedPayments.add(PaymentMapper.convertToDto(pa));
		});
		return managedPayments;
	}

}
