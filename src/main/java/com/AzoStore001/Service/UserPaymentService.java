package com.AzoStore001.Service;

import com.AzoStore001.Model.UserPayment;

public interface UserPaymentService {
	
	UserPayment getOne(Long id);
	void removeById(Long id);

}
