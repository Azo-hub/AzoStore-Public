package com.AzoStore001.Service;

import com.AzoStore001.Model.ShippingAddress;
import com.AzoStore001.Model.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
	

}
