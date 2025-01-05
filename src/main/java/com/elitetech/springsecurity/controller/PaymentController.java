package com.elitetech.springsecurity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elitetech.springsecurity.dto.PaymentDTO;
import com.elitetech.springsecurity.service.interfaces.PaymentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Validated
public class PaymentController {
private final PaymentService paymentService;
@PostMapping("/process/{orderId}")
public ResponseEntity<PaymentDTO> processPayment(@PathVariable @Min(1)long orderId,@Valid  @RequestBody PaymentDTO paymentDTO)
{
return ResponseEntity.ok(paymentService.processPayment(orderId, paymentDTO));	

}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteOnePayment(@PathVariable @Min(1) long id)
{
paymentService.deletePayment(id);
return ResponseEntity.noContent().build();
}
@GetMapping("/{id}")
public ResponseEntity<PaymentDTO> getOnePayment(@PathVariable @Min(1) long id)
{
PaymentDTO payment=paymentService.getPaymentById(id);
if(payment==null)
	return ResponseEntity.notFound().build();
return ResponseEntity.ok(payment);
}
@GetMapping
public ResponseEntity<List<PaymentDTO>> getAllPayments()
{
return ResponseEntity.ok(paymentService.getAllPayments());	

}


}
