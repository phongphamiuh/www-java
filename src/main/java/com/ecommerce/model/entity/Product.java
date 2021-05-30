package com.ecommerce.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@ToString(exclude = {"productCategory"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory productCategory;

	/*
	 * @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL,fetch =
	 * FetchType.EAGER) private List<OrderDetail> orderDetails;
	 */

    @Basic(fetch= FetchType.LAZY)
    @Column(name="image")
    @Lob
    private MultipartFile image;

    private String name;
    
    private Float price;
    
    private int stock;
    
    private Float onSalePrice;
    
    private Float cargoPrice;
    
    private String brand;
    
    private String description;
    
    private Date dateCreated;

}
