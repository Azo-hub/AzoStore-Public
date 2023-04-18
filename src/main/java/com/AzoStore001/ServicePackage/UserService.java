package com.AzoStore001.ServicePackage;

import java.util.Set;

import com.AzoStore001.ModelPackage.PasswordResetToken;
import com.AzoStore001.ModelPackage.User;
import com.AzoStore001.ModelPackage.UserBilling;
import com.AzoStore001.ModelPackage.UserPayment;
import com.AzoStore001.ModelPackage.UserRole;
import com.AzoStore001.ModelPackage.UserShipping;



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
