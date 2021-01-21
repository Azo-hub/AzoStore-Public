package AzoStore.ServicePackage;

import AzoStore.ModelPackage.Payment;
import AzoStore.ModelPackage.UserPayment;

public interface PaymentService {
	Payment setByUserPayment(UserPayment userPayment, Payment payment);
	

}
