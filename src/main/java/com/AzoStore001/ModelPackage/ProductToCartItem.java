package com.AzoStore001.ModelPackage;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class ProductToCartItem {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cart_item_id")
	private CartItem cartItem;
	
	

}
