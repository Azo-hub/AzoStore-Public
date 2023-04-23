package com.AzoStore001.Service;

import com.AzoStore001.Model.Payment;
import com.AzoStore001.Model.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
	

}
