package AzoStore.ServicePackage;

import AzoStore.ModelPackage.UserPayment;

public interface UserPaymentService {
	
	UserPayment getOne(Long id);
	void removeById(Long id);

}
