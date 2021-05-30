package com.ecommerce.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @ToString.Exclude
    @Fetch(value = FetchMode.SUBSELECT)
    private List<CartItem> cartItemList;

    @Column(name = "total_cart_price")
    private Float totalCartPrice;

    @Column(name = "total_cargo_price")
    private Float totalCargoPrice;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "date_created", insertable = false)
    private Date dateCreated;

}
