package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.UserShipping;

public interface UserShippingService {
	
	UserShipping getOne(Long id);
	void removeById(Long id);


}
