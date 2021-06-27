package com.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.request.OrderRequest;
import com.ecommerce.model.response.CartSession;
import com.ecommerce.model.response.OrderResponse;

public interface OrderService {
	
	Integer getAllOrdersCount();

    List<OrderResponse> getAllOrders(Integer page, Integer pageSize);

    OrderResponse postOrder(OrderRequest orderRequest, CartSession cart);
    
    Page<Order> getAll(int page, int pageSize, String sortField, String sortDirection,String status);
    
    OrderResponse getOrder(Long id);
}
