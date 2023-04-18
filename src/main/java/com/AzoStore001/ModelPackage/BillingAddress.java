package com.AzoStore001.ModelPackage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class BillingAddress {
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String billingAddressName;
	private String billingAddressStreet1;
	private String billingAddressStreet2;
	private String billingAddressCity;
	private String billingAddressState;
	private String billingAddressCountry;
	private String billingAddressZipCode;
	
	@OneToOne
	private Order order;

}
