package com.ecommerce.model.response;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
	private Long id;
    private String categoryName;
    private String name;
    private Float price;
    private int stock;
    private Float onSalePrice;
    private Float cargoPrice;
    private String brand;
    private String description;
}
