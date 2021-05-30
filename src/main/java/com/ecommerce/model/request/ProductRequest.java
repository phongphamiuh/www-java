package com.ecommerce.model.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.entity.OrderDetail;
import com.ecommerce.model.response.ProductResponse;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class ProductRequest {
	
	@NotEmpty
    private String categoryName;
	
	@NotEmpty(message = "Tên sản phẩm không được để trống")
    private String name;
	
	@NotNull(message="Giá không được để trống")
    private Float price;
	
    private int stock;
	
    private Float onSalePrice;
    
    private Float cargoPrice;
    
    private String brand;
   
    private String description;
    
    private MultipartFile image;
}
