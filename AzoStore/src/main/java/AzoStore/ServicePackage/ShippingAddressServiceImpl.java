package AzoStore.ServicePackage;

import org.springframework.stereotype.Service;

import AzoStore.ModelPackage.ShippingAddress;
import AzoStore.ModelPackage.UserShipping;


@Service
public class ShippingAddressServiceImpl implements ShippingAddressService{

	@Override
	public ShippingAddress setByUserShipping(UserShipping userShipping, ShippingAddress shippingAddress) {
		// TODO Auto-generated method stub
		shippingAddress.setShippingAddressName(userShipping.getUserShippingName());
		shippingAddress.setShippingAddressStreet1(userShipping.getUserShippingStreet1());
		shippingAddress.setShippingAddressStreet2(userShipping.getUserShippingStreet2());
		shippingAddress.setShippingAddressCity(userShipping.getUserShippingCity());
		shippingAddress.setShippingAddressState(userShipping.getUserShippingState());
		shippingAddress.setShippingAddressCountry(userShipping.getUserShippingCountry());
		shippingAddress.setShippingAddressZipCode(userShipping.getUserShippingZipCode());
		
		
		return shippingAddress;
	}

}
