package com.elitetech.springsecurity.service.interfaces;

import com.elitetech.springsecurity.dto.OrderDTO;
import com.elitetech.springsecurity.entity.Commande;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(long userId, OrderDTO orderDTO);

    OrderDTO addProductToOrder(long orderId, long productId);

    void deleteOrder(Long id);

    OrderDTO getOrderById(Long id);

    List<OrderDTO> getAllOrders();
}
