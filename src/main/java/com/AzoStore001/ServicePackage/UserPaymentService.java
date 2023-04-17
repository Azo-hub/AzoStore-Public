package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.UserPayment;

public interface UserPaymentService {
	
	UserPayment getOne(Long id);
	void removeById(Long id);

}
