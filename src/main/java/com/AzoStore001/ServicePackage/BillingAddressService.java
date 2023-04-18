package com.AzoStore001.ServicePackage;

import com.AzoStore001.ModelPackage.BillingAddress;
import com.AzoStore001.ModelPackage.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}
