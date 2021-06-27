package com.ecommerce.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.sql.ordering.antlr.OrderingSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.converter.product.OrderResponseConverter;
import com.ecommerce.converter.specs.OrderSpecification;
import com.ecommerce.exception.BadRequestException;
import com.ecommerce.model.entity.Cart;
import com.ecommerce.model.entity.Order;
import com.ecommerce.model.entity.OrderDetail;
import com.ecommerce.model.entity.Product;
import com.ecommerce.model.entity.User;
import com.ecommerce.model.request.OrderRequest;
import com.ecommerce.model.response.CartSession;
import com.ecommerce.model.response.OrderResponse;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.StatusRepository;
import com.ecommerce.service.CartService;
import com.ecommerce.service.OrderService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{
	
	private final UserService userService;
	private final OrderRepository orderRepository;
	private final OrderResponseConverter orderResponseConverter;
//	private final CartService cartService;
	private final OrderSpecification orderSpecification;
	private final StatusRepository statusRepository;
	private final ProductService productService;
//	@Override
//    public Integer getAllOrdersCount() {
//        User user = userService.getUser();
//        return orderRepository.countAllByUser(user)
//                .orElseThrow(() -> new BadRequestException("An error occurred whilst fetching orders count"));
//    }
//
//    @Override
//    public List<OrderResponse> getAllOrders(Integer page, Integer pageSize) {
//        User user = userService.getUser();
//        List<Order> orders = orderRepository.findAllByUserOrderByDateDesc(user, PageRequest.of(page, pageSize));
//        return orders
//                .stream()
//                .map(orderResponseConverter)
//                .collect(Collectors.toList());
//    }

    @Override
    public OrderResponse postOrder(OrderRequest orderRequest, CartSession cart) {
        User user = userService.getUser();
        String newStatus = "Đặt hàng";
      //  Cart cart = user.getCart();
        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItems())) {
            throw new BadRequestException("Cart is not valid");
        }

        if (cart.getCartItems().stream().anyMatch(cartItem -> cartItem.getStock() < cartItem.getAmount())) {
            throw new BadRequestException("A product in your cart is out of stock.");
        }
       
        Order saveOrder = new Order();
        saveOrder.setUser(user);
        
        saveOrder.setShipName(orderRequest.getShipName());
        saveOrder.setPhone(orderRequest.getPhone());
        saveOrder.setCity(orderRequest.getCity());
        saveOrder.setDistrict(orderRequest.getDistrict());
        saveOrder.setStreet(orderRequest.getStreet());
        saveOrder.setShipAddrees(orderRequest.getShipAddress());
        saveOrder.setStatus(statusRepository.findByName(newStatus));
        LocalDate date= LocalDate.now();
        saveOrder.setCreateDate(date);

        saveOrder.setOrderDetailList(new ArrayList<>());

        cart.getCartItems().forEach(cartItem -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setAmount(cartItem.getAmount());
            orderDetail.setOrder(saveOrder);
            orderDetail.setProduct(productService.findById(cartItem.getProductId()));
            saveOrder.getOrderDetailList().add(orderDetail);
        });

        saveOrder.setTotalPrice(cart.getTotalPrice());
        saveOrder.setTotalCargoPrice(cart.getTotalCargoPrice());
        Order order = orderRepository.save(saveOrder);
        System.out.println("Order :"+order);
      //  cartService.emptyCart();
        return orderResponseConverter.apply(order);
    }

	@Override
	public Integer getAllOrdersCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderResponse> getAllOrders(Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Order> getAll(int page, int pageSize, String sortField, String sortDirection, String status) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : 
			Sort.by(sortField).descending();
		
		PageRequest pageRequest;
        if (Objects.nonNull(sortDirection)) {
            Sort sortRequest = sort;
            if (Objects.isNull(sortRequest)) {
                throw new BadRequestException("Invalid sort parameter");
            }
            pageRequest = PageRequest.of(page -1, pageSize, sortRequest);
        } else {
            pageRequest = PageRequest.of(page -1, pageSize);
        }
        
        @SuppressWarnings("static-access")
        Specification<Order> combinations=
        Objects.requireNonNull(Specification.where(orderSpecification.withStatus(status)));

      //  Pageable pageable = PageRequest.of(pageNo - 1, pageSize,Sort.by("onSalePrice").ascending());
       return orderRepository.findAll(combinations, pageRequest);
       
	}

	@Override
	public OrderResponse getOrder(Long id) {
		Order order= orderRepository.findById(id).orElseThrow(()->new BadRequestException("Order not found!"));
		return orderResponseConverter.apply(order);
	}

}
