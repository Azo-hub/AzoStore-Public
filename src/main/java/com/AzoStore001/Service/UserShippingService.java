package com.AzoStore001.Service;

import com.AzoStore001.Model.UserShipping;

public interface UserShippingService {
	
	UserShipping getOne(Long id);
	void removeById(Long id);


}
