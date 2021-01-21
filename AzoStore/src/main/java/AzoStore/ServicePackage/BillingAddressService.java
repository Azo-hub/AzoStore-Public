package AzoStore.ServicePackage;

import AzoStore.ModelPackage.BillingAddress;
import AzoStore.ModelPackage.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}
