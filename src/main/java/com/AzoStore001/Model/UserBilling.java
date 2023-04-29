package com.AzoStore001.Model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class UserBilling  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String userBillingName;
	private String userBillingStreet1;
	private String userBillingStreet2;
	private String userBillingCity;
	private String userBillingState;
	private String userBillingCountry;
	private String userBillingZipCode;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserPayment userPayment;

}
