package com.ecommerce.converter.product;

import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerce.model.dto.CartItemDto;
import com.ecommerce.model.dto.OrderDetailDto;
import com.ecommerce.model.entity.Order;
import com.ecommerce.model.response.OrderResponse;
@Component
public class OrderResponseConverter implements Function<Order, OrderResponse>{

	@Override
	public OrderResponse apply(Order order) {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setId(order.getId());
		orderResponse.setCity(order.getCity());
		orderResponse.setShipName(order.getShipName());
		orderResponse.setShipAddrees(order.getShipAddrees());
		orderResponse.setPhone(order.getPhone());
		orderResponse.setDistrict(order.getCity());
		orderResponse.setDistrict(order.getDistrict());
		  
		orderResponse.setOrderDetailList(order.getOrderDetailList()
                .stream()
                .map(orderDetail -> OrderDetailDto
                        .builder()
                        .name(orderDetail.getProduct().getName())
                        .price(orderDetail.getProduct().getPrice())
                        .cargoPrice(orderDetail.getProduct().getCargoPrice())
                        .onSalePrice(orderDetail.getProduct().getOnSalePrice())
                        .amount(orderDetail.getAmount())
                        .build())
                .collect(Collectors.toList()));
		return orderResponse;
	}
	
}
