package com.AzoStore001.ServicePackage;

import java.util.Set;

import AzoStore1.ModelPackage.PasswordResetToken;
import AzoStore1.ModelPackage.User;
import AzoStore1.ModelPackage.UserBilling;
import AzoStore1.ModelPackage.UserPayment;
import AzoStore1.ModelPackage.UserRole;
import AzoStore1.ModelPackage.UserShipping;



public interface UserService {
	PasswordResetToken getPasswordResetToken (final String token);
	
	void createPasswordResetTokenForUser (final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	User findById (Long id);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	
	void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);
	
	void setUserDefaultPayment (Long userPaymentId, User user);
	
	
	void updateUserShipping(UserShipping userShipping, User user);
	
	void setUserDefaultShipping (Long defaultShippingId, User user);
	
}
