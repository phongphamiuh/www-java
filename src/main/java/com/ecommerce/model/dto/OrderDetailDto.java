package com.ecommerce.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderDetailDto {

    private String name;

    private Float price;

    private Float cargoPrice;
    
    private Float onSalePrice;

    private Integer amount;

}
