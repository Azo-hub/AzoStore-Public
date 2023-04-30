package com.AzoStore001.Service;

import java.util.Set;

import com.AzoStore001.Model.AuthenticationProvider;
import com.AzoStore001.Model.PasswordResetToken;
import com.AzoStore001.Model.User;
import com.AzoStore001.Model.UserBilling;
import com.AzoStore001.Model.UserPayment;
import com.AzoStore001.Model.UserRole;
import com.AzoStore001.Model.UserShipping;



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

	void increaseFailedAttempt(User user);

	void lock(User user);

	boolean unlock(User user);

	void resetFailedAttempts(String username);

	void updateUserShipping(UserShipping userShipping, User user);
	
	void setUserDefaultShipping (Long defaultShippingId, User user);

	void createNewUserAfterOAuthLoginSuccess(Set<UserRole> userRoles, String email, String name,
			AuthenticationProvider provider);

	void updateUserAfterOAuthLoginSuccess(User user, String name, AuthenticationProvider provider);


}
