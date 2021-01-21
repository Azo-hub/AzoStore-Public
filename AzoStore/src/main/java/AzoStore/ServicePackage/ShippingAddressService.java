package AzoStore.ServicePackage;

import AzoStore.ModelPackage.ShippingAddress;
import AzoStore.ModelPackage.UserShipping;

public interface ShippingAddressService {
	ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress);
	

}
