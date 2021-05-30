package com.ecommerce.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_image")
@Data
@NoArgsConstructor
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image")
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name="product_id",referencedColumnName = "product_id")
    private Product product;
}
