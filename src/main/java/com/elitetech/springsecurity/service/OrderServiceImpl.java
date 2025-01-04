package com.elitetech.springsecurity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.elitetech.springsecurity.dto.OrderDTO;
import com.elitetech.springsecurity.dto.ProductDTO;
import com.elitetech.springsecurity.dto.UserDTO;
import com.elitetech.springsecurity.entity.Commande;
import com.elitetech.springsecurity.mapper.OrderMapper;
import com.elitetech.springsecurity.mapper.ProductMapper;
import com.elitetech.springsecurity.mapper.UserInfoMapper;
import com.elitetech.springsecurity.repository.OrderRepository;
import com.elitetech.springsecurity.repository.ProductRepository;
import com.elitetech.springsecurity.repository.UserInfoRepository;
import com.elitetech.springsecurity.service.interfaces.OrderService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
private OrderRepository orderRepository;
private UserInfoRepository userInfoRepository;
private ProductRepository productRepository;
	
	
	@Override
	public OrderDTO createOrder(long userId, OrderDTO orderDTO) {
		// TODO Auto-generated method stub
		// lire userInfo + convert user to dto assign user to order
		// save order
		if(userInfoRepository.existsById(userId))
		{
			UserDTO currentUser=UserInfoMapper.convertToDto(userInfoRepository.findById(userId).get());
			orderDTO.setUser(currentUser);
			Commande ord=orderRepository.save(OrderMapper.convertToEntity(orderDTO));
			return OrderMapper.convertToDto(ord);
		}
		
		
		return null;
	}

	@Override
	public OrderDTO addProductToOrder(long orderId, long productId) {
		// TODO Auto-generated method stub
		if(productRepository.existsById(productId))
		{
			ProductDTO prod=ProductMapper.convertToDto(productRepository.findById(productId).get());
			if(orderRepository.existsById(orderId))
			{
				OrderDTO ord= OrderMapper.convertToDto(orderRepository.findById(orderId).get());
				Set<ProductDTO> prods=ord.getProducts();
				prods.add(prod);
				return OrderMapper.convertToDto(orderRepository.save(OrderMapper.convertToEntity(ord)));
				
			}
			
		}
		
		return null;
	}

	@Override
	public void deleteOrder(Long id) {
		// TODO Auto-generated method stub
		orderRepository.deleteById(id);

	}

	@Override
	public OrderDTO getOrderById(Long id) {
		// TODO Auto-generated method stub
		return OrderMapper.convertToDto(orderRepository.findById(id).orElse(null));
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		// TODO Auto-generated method stub
		List<Commande> orders=orderRepository.findAll();
		List<OrderDTO> managedOrders= new ArrayList<OrderDTO>();
		orders.forEach(ord->{
			managedOrders.add(OrderMapper.convertToDto(ord));
		});
		
		return managedOrders;
	}

}
