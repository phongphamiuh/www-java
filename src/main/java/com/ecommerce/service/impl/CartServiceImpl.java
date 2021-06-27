//package com.ecommerce.service.impl;
//
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//import org.springframework.stereotype.Service;
//
//import com.ecommerce.converter.product.CartResponseConverter;
//import com.ecommerce.converter.product.ProductResponseConverter;
//import com.ecommerce.exception.BadRequestException;
//import com.ecommerce.model.entity.Cart;
//import com.ecommerce.model.entity.CartItem;
//import com.ecommerce.model.entity.Product;
//import com.ecommerce.model.entity.User;
//import com.ecommerce.model.response.CartResponse;
//import com.ecommerce.repository.CartRepository;
//import com.ecommerce.repository.ProductRepository;
//import com.ecommerce.service.CartService;
//import com.ecommerce.service.ProductService;
//import com.ecommerce.service.UserService;
//
//import lombok.AllArgsConstructor;
//
//@Service
//@AllArgsConstructor
//public class CartServiceImpl implements CartService{
//	
//	private final UserService userService;
//	private final CartRepository cartRepository;
//	private final CartResponseConverter cartResponseConverter;
//	private final ProductService productService;
//
//	
//	@Override
//	public CartResponse addToCart(Long id, Integer amount) {
//		
//		User user = userService.getUser();
//		System.out.println(user);
//		Cart cart = user.getCart();
//		
//		if (Objects.nonNull(cart) && Objects.nonNull(cart.getCartItemList()) && !cart.getCartItemList().isEmpty()) {
//            Optional<CartItem> cartItem = cart.getCartItemList()
//                    .stream()
//                    .filter(ci -> ci.getProduct().getId().equals(id))
//                    .findFirst();
//            
//            if (cartItem.isPresent()) {
//                if (cartItem.get().getProduct().getStock() < (cartItem.get().getAmount() + amount)) {
//                    throw new BadRequestException("Product does not have desired stock.");
//                }
//                cartItem.get()
//                	.setAmount(cartItem.get().getAmount() + amount);
//                
//                Cart updatedCart = calculatePrice(cart);
//                cart = cartRepository.save(updatedCart);
//                return cartResponseConverter.apply(cart);
//            }
//        }
//
//        if (Objects.isNull(cart)) {
//            cart = createCart(user);
//        }
//
//        Product product = productService.findById(id);
//      
//        if (product.getStock() < amount) {
//            throw new BadRequestException("Product does not have desired stock.");
//        }
//
//        CartItem cartItem = new CartItem();
//        cartItem.setAmount(amount);
//        cartItem.setProduct(product);
//        cartItem.setCart(cart);
//
//        if (Objects.isNull(cart.getCartItemList())) {
//            cart.setCartItemList(new ArrayList<>());
//        }
//        
//        cart.getCartItemList().add(cartItem);
//        cart = calculatePrice(cart);
//        System.out.println("Cart Service :" + cart);
//        cart = cartRepository.save(cart);
//        return cartResponseConverter.apply(cart);
//	}
//
//	@Override
//	public CartResponse incrementCartItem(Long cartItemId, Integer amount) {
//		User user = userService.getUser();
//        Cart cart = user.getCart();
//        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//            throw new BadRequestException("Empty cart");
//        }
//
//        CartItem cartItem = cart.getCartItemList()
//                .stream()
//                .filter(ci -> ci.getId().equals(cartItemId))
//                .findFirst()
//                .orElseThrow(() -> new BadRequestException("CartItem not found"));
//
//        if (cartItem.getProduct().getStock() < (cartItem.getAmount() + amount)) {
//            throw new BadRequestException("Product does not have desired stock.");
//        }
//
//        cartItem.setAmount(cartItem.getAmount() + amount);
//        cart = calculatePrice(cart);
//        cart = cartRepository.save(cart);
//        return cartResponseConverter.apply(cart);
//	}
//
//	@Override
//	public CartResponse decrementCartItem(Long cartItemId, Integer amount) {
//		 User user = userService.getUser();
//	        Cart cart = user.getCart();
//	        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//	            throw new BadRequestException("Empty cart");
//	        }
//
//	        CartItem cartItem = cart.getCartItemList()
//	                .stream()
//	                .filter(ci -> ci.getId().equals(cartItemId))
//	                .findFirst()
//	                .orElseThrow(() -> new BadRequestException("CartItem not found"));
//
//
//	        if (cartItem.getAmount() <= amount) {
//	            List<CartItem> cartItemList = cart.getCartItemList();
//	            cartItemList.remove(cartItem);
//	            if (Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//	                user.setCart(null);
//	                userService.saveUser(user);
//	                return null;
//	            }
//	            cart.setCartItemList(cartItemList);
//	            cart = calculatePrice(cart);
//	            cart = cartRepository.save(cart);
//	            return cartResponseConverter.apply(cart);
//	        }
//
//	        cartItem.setAmount(cartItem.getAmount() - amount);
//	        cart = calculatePrice(cart);
//	        cart = cartRepository.save(cart);
//	        return cartResponseConverter.apply(cart);
//	}
//
//	@Override
//	public CartResponse fetchCart() {
//		CartResponse cartReposnse = null;
//		User user = userService.getUser();
//		
//		if(user == null) {
//			
//		}
//		else {
//			Cart cart = user.getCart();
//	        if (cart == null) {
//	            return null;
//	        }
//		    cartReposnse = cartResponseConverter.apply(cart);
//		}
//		return cartReposnse;
//	}
//
//	@Override
//	public CartResponse removeFromCart(Long id) {
//		User user = userService.getUser();
//        Cart cart = user.getCart();
//
//        if (Objects.isNull(cart) || Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
//            throw new BadRequestException("Cart or CartItem not found");
//        }
//
//        List<CartItem> cartItemsList = cart.getCartItemList();
//        System.out.println("List Cart Item :"+cartItemsList);
//        Optional<CartItem> cartItem = cart.getCartItemList()
//                .stream()
//                .filter(ci -> ci.getId().equals(id))
//                .findFirst();
//        
//        if (!cartItem.isPresent()) {
//            throw new BadRequestException("CartItem not found");
//        }
//
//        cartItemsList.remove(cartItem.get());
//
////        if (Objects.isNull(cart.getCartItemList()) || cart.getCartItemList().isEmpty()) {
////            user.setCart(null);
////            userService.saveUser(user);
////            return null;
////        }
//        
//        cart.setCartItemList(cartItemsList);
//        cart = calculatePrice(cart);
//        cart = cartRepository.save(cart);
//        return cartResponseConverter.apply(cart);
//	}
//
//	@Override
//	public Cart getCart() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void saveCart(Cart cart) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void emptyCart() {
//		User user = userService.getUser();
//        user.setCart(null);
//        userService.saveUser(user);
//	}
//
//	@Override
//    public Cart calculatePrice(Cart cart) {
//        cart.setTotalCartPrice(0F);
//        cart.setTotalCargoPrice(0F);
//        cart.setTotalPrice(0F);
//
//        cart.getCartItemList().forEach(cartItem -> {
//            cart.setTotalCartPrice(cart.getTotalCartPrice() + (cartItem.getProduct().getOnSalePrice()) * cartItem.getAmount());
//            cart.setTotalCargoPrice(cart.getTotalCargoPrice() + (cartItem.getProduct().getCargoPrice()) * cartItem.getAmount());
//            cart.setTotalPrice(
//                    cart.getTotalPrice() +
//                            (cartItem.getProduct().getOnSalePrice() + cartItem.getProduct().getCargoPrice()) * cartItem.getAmount());
//        });
//
//       
//        cart.setTotalPrice(roundTwoDecimals(cart.getTotalPrice()));
//        cart.setTotalCargoPrice(roundTwoDecimals(cart.getTotalCargoPrice()));
//        return cart;
//    }
//	
//	 private float roundTwoDecimals(float d) {
//	        DecimalFormat twoDForm = new DecimalFormat("#.##");
//	        return Float.parseFloat(twoDForm.format(d));
//	}
//	
//	 private Cart createCart(User user) {
//	        Cart cart = new Cart();
//	        cart.setUser(user);
//	        return cart;
//	 }
//	
//}
