package com.AzoStore001.ServicePackage;

import com.AzoStore001.ModelPackage.ShippingAddress;
import com.AzoStore001.ModelPackage.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
	

}
