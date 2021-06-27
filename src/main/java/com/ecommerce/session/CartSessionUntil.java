package com.ecommerce.session;

import javax.servlet.http.HttpServletRequest;

import com.ecommerce.model.response.CartResponse;
import com.ecommerce.model.response.CartSession;

public class CartSessionUntil {
	 // Products in Cart, stored in Session.
    public static CartSession getCartInSession(HttpServletRequest  request) {
        // Get Cart from Session.
    	CartSession cartInfo = (CartSession) request.getSession().getAttribute("myCart");
        
        // If null, create it.
        if (cartInfo == null) {
            cartInfo = new CartSession();           
            // And store to Session.    
            request.getSession().setAttribute("myCart", cartInfo);
            System.out.println("session ID"+request.getSession().getId());
        }
        return cartInfo;
    }
    
    public static void removeCartInSession(HttpServletRequest request) {
        request.getSession().removeAttribute("myCart");
    }
 
    public static void storeLastOrderedCartInSession(HttpServletRequest request, CartSession cartInfo) {
        request.getSession().setAttribute("lastOrderedCart", cartInfo);
    }
    
    public static CartSession getLastOrderedCartInSession(HttpServletRequest request) {
        return (CartSession) request.getSession().getAttribute("lastOrderedCart");
    }
}
