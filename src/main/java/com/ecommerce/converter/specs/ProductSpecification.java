package com.ecommerce.converter.specs;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ecommerce.model.entity.Product;

@Component
public final class ProductSpecification {
	
	public static Specification<Product> withCategory(String categoryName){
        return (root,query,cb) ->{
            if(categoryName==null){
                return cb.isTrue(cb.literal(true));
            }
            return cb.equal(root.join("productCategory").get("name"),categoryName);
        };
    }
}
