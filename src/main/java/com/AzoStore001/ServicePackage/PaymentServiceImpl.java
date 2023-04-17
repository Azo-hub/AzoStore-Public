package com.AzoStore001.ServicePackage;

import org.springframework.stereotype.Service;

import AzoStore1.ModelPackage.Payment;
import AzoStore1.ModelPackage.UserPayment;



@Service
public class PaymentServiceImpl implements PaymentService {

	
	@Override
	public Payment setByUserPayment(UserPayment userPayment, Payment payment) {
		// TODO Auto-generated method stub
		payment.setType(userPayment.getType());
		payment.setHolderName(userPayment.getHolderName());
		payment.setCardNumber(userPayment.getCardNumber());
		payment.setExpiryMonth(userPayment.getExpiryMonth());
		payment.setExpiryYear(userPayment.getExpiryYear());
		payment.setCvc(userPayment.getCvc());

		return payment;
		
	}
	

}
