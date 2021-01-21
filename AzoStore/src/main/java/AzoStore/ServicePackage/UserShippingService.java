package AzoStore.ServicePackage;

import AzoStore.ModelPackage.UserShipping;

public interface UserShippingService {
	
	UserShipping getOne(Long id);
	void removeById(Long id);


}
