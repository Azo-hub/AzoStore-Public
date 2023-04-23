package com.AzoStore001.Model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int qty;
	private BigDecimal subtotal;
	
	@OneToOne
	private Product product;
	
	@OneToMany(mappedBy = "cartItem")
	@JsonIgnore
	private List<ProductToCartItem> productTocartItemList;
	
	
	@ManyToOne
	@JoinColumn(name = "shopping_cart_id")
	private ShoppingCart shoppingCart;
	
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

}

