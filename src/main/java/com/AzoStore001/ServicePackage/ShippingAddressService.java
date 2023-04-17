package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.ShippingAddress;
import AzoStore1.ModelPackage.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
	

}
