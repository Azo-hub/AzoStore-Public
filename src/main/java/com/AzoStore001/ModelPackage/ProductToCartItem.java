package com.AzoStore001.ModelPackage;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
