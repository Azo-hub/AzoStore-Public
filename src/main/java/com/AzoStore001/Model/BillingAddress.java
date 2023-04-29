package com.AzoStore001.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
