package com.elitetech.springsecurity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.entity.Product;
import com.elitetech.springsecurity.mapper.ProductMapper;
import com.elitetech.springsecurity.repository.ProductRepository;
import com.elitetech.springsecurity.service.interfaces.ProductService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

	private final ProductRepository productRepository;
	
	@Override
	public ProductDTO addProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		
		return ProductMapper.convertToDto(productRepository.save(ProductMapper.convertToEntity(productDTO)));
	}

	@Override
	public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
		// TODO Auto-generated method stub
		if(productRepository.existsById(id))
		{
			return ProductMapper.convertToDto(productRepository.save(ProductMapper.convertToEntity(productDTO)));
		}
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		if(productRepository.existsById(id))
		{
		productRepository.deleteById(id);
		}

	}

	@Override
	public ProductDTO getProductById(Long id) {
		// TODO Auto-generated method stub
		return ProductMapper.convertToDto(productRepository.findById(id).orElse(null));
	}

	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products=productRepository.findAll();
		List<ProductDTO> managedProducts=new ArrayList<ProductDTO>();
		products.forEach(pr->{
			managedProducts.add(ProductMapper.convertToDto(pr));
		});
		return managedProducts;
	}

}
