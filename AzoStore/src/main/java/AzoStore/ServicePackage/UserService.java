package AzoStore.ServicePackage;

import java.util.Set;

import AzoStore.ModelPackage.PasswordResetToken;
import AzoStore.ModelPackage.User;
import AzoStore.ModelPackage.UserBilling;
import AzoStore.ModelPackage.UserPayment;
import AzoStore.ModelPackage.UserRole;
import AzoStore.ModelPackage.UserShipping;


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
	
}
