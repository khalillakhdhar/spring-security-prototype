package com.elitetech.springsecurity.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.service.interfaces.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
public class ProductController {
private final ProductService productService;

public ResponseEntity<ProductDTO> addOneProduct(@Valid @RequestBody ProductDTO productDTO)
{
return ResponseEntity.ok(productService.addProduct(productDTO));	
}
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProduct(@PathVariable @Min(1) long id)
{
productService.deleteProduct(id);
return ResponseEntity.noContent().build();
}

@GetMapping("/{id}")
public ResponseEntity<ProductDTO> getOneProduct(@PathVariable @Min(1) long id)
{
ProductDTO product=productService.getProductById(id);
if(product==null)
{
return ResponseEntity.notFound().build();	
}
return ResponseEntity.ok(product);
}
@GetMapping
public ResponseEntity<List<ProductDTO>> getAllProducts()
{
return ResponseEntity.ok(productService.getAllProducts());	
}
}
