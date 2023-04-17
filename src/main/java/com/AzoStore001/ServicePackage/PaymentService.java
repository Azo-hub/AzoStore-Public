package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.Payment;
import AzoStore1.ModelPackage.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
	

}
