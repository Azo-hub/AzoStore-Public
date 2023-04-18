package com.AzoStore001.ServicePackage;

import com.AzoStore001.ModelPackage.UserPayment;

public interface UserPaymentService {
	
	UserPayment getOne(Long id);
	void removeById(Long id);

}
