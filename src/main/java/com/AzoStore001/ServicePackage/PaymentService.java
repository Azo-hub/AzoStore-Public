package com.AzoStore001.ServicePackage;

import com.AzoStore001.ModelPackage.Payment;
import com.AzoStore001.ModelPackage.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
	

}
