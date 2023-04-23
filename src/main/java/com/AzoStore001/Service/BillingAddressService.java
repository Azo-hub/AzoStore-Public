package com.AzoStore001.Service;

import com.AzoStore001.Model.BillingAddress;
import com.AzoStore001.Model.UserBilling;

public interface BillingAddressService {
	BillingAddress setByUserBilling(UserBilling userBilling, BillingAddress billingAddress);

}
