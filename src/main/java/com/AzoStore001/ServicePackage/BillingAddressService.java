package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.BillingAddress;
import AzoStore1.ModelPackage.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}
