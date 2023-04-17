package com.AzoStore001.ServicePackage;

import org.springframework.stereotype.Service;

import AzoStore1.ModelPackage.BillingAddress;
import AzoStore1.ModelPackage.UserBilling;



@Service
public class BillingAddressServiceImpl implements BillingAddressService{

	@Override
	public BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress) {
		// TODO Auto-generated method stub
		billingAddress.setBillingAddressName(userBilling.getUserBillingName());
		billingAddress.setBillingAddressStreet1(userBilling.getUserBillingStreet1());
		billingAddress.setBillingAddressStreet2(userBilling.getUserBillingStreet2());
		billingAddress.setBillingAddressCity(userBilling.getUserBillingCity());
		billingAddress.setBillingAddressState(userBilling.getUserBillingState());
		billingAddress.setBillingAddressCountry(userBilling.getUserBillingCountry());
		billingAddress.setBillingAddressZipCode(userBilling.getUserBillingZipCode());
		
		
		return billingAddress;
	}

}
