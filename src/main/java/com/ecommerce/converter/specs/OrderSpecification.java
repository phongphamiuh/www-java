package com.ecommerce.converter.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ecommerce.model.entity.Order;

@Component
public final class OrderSpecification {
	
	public static Specification<Order> withStatus(String status){
        return (root,query,cb) ->{
            if(status==null){
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.join("status").get("name"),status);
        };
    }
}
