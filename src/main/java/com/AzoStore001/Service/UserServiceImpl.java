package com.AzoStore001.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.Model.AuthenticationProvider;
import com.AzoStore001.Model.PasswordResetToken;
import com.AzoStore001.Model.ShoppingCart;
import com.AzoStore001.Model.User;
import com.AzoStore001.Model.UserBilling;
import com.AzoStore001.Model.UserPayment;
import com.AzoStore001.Model.UserRole;
import com.AzoStore001.Model.UserShipping;
import com.AzoStore001.Repository.PasswordResetTokenRepository;
import com.AzoStore001.Repository.RoleRepository;
import com.AzoStore001.Repository.UserPaymentRepository;
import com.AzoStore001.Repository.UserRepository;
import com.AzoStore001.Repository.UserShippingRepository;


@Service
public class UserServiceImpl implements UserService {
	
	public static final int MAX_FAILED_ATTEMPTS = 3;
	private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000;

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@Autowired
	private UserShippingRepository userShippingRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);

	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken myToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(myToken);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User localUser = userRepository.findByUsername(user.getUsername());

		if (localUser != null) {
			// throw new Exception("User already exists. Nothing will be done");

			LOG.info("User {} already exists. Nothing will be done.", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);
			
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setUser(user);
			user.setShoppingCart(shoppingCart);
			
			user.setUserShippingList (new ArrayList <UserShipping> ());
			
			user.setUserPaymentList (new ArrayList <UserPayment> ());
			

			localUser = userRepository.save(user);

		}

		return localUser;

	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		// TODO Auto-generated method stub
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);

	}
	
	
	
	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
		// TODO Auto-generated method stub

		List<UserPayment> userPaymentList = userPaymentRepository.findAll();

		for (UserPayment userPayment : userPaymentList) {
			
			if (userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			
			} else {
				
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			
			}
		}

	}
	
	

	@Override
	public void updateUserShipping(UserShipping userShipping, User user) {
		// TODO Auto-generated method stub

		userShipping.setUser(user);
		userShipping.setUserShippingDefault(true);
		user.getUserShippingList().add(userShipping);
		save(user);

	}

	
	@Override
	public void setUserDefaultShipping(Long defaultShippingId, User user) {
		// TODO Auto-generated method stub

		List<UserShipping> userShippingList = userShippingRepository.findAll();

		for (UserShipping userShipping : userShippingList) {
			
			if (userShipping.getId() == defaultShippingId) {
				userShipping.setUserShippingDefault(true);
				userShippingRepository.save(userShipping);
			
			} else {
				
				userShipping.setUserShippingDefault(false);
				userShippingRepository.save(userShipping);
			
			}
		}

	}

	
	
	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.getOne(id);
	}

	@Override
	public void increaseFailedAttempt(User user) {
		long newFailedAttempts = user.getFailedAttempt() + 1;
		
		userRepository.updateFailedAttempt(newFailedAttempts, user.getUsername());
		
		
		
	}

	@Override
	public void lock(User user) {
		
		user.setAccountNonLocked(false);
		user.setLockTime(new Date());
		
		userRepository.save(user);
		
	}
	
	@Override
	public boolean unlock(User user) {
		
		long lockTimeInMillis = user.getLockTime().getTime();
		long currentTimeInMillis = System.currentTimeMillis();
		
		if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
			
			user.setAccountNonLocked(true);
			user.setLockTime(null);
			user.setFailedAttempt((long) 0);
			
			userRepository.save(user);
			
			return true;
		}
		
		return false;
		
	}

	@Override
	public void resetFailedAttempts(String username) {
		
		userRepository.updateFailedAttempt(0, username);
		
	}

	@Override
	public void createNewUserAfterOAuthLoginSuccess(Set<UserRole> userRoles, String email, String name, AuthenticationProvider provider) {
		// TODO Auto-generated method stub
		
		User user = new User();
		user.setEmail(email);
		user.setFirstname(name);
		user.setAccountNonLocked(true);
		//user.setLockTime(null);
		user.setFailedAttempt((long) 0);
		
		user.setOauth_provider(provider);
		user.setUsername(name);
		
		for (UserRole ur : userRoles) {
			roleRepository.save(ur.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		user.setShoppingCart(shoppingCart);
		
		user.setUserShippingList (new ArrayList <UserShipping> ());
		
		user.setUserPaymentList (new ArrayList <UserPayment> ());

		userRepository.save(user);

	}

	@Override
	public void updateUserAfterOAuthLoginSuccess(User user, String name, AuthenticationProvider provider) {
		// TODO Auto-generated method stub
		user.setUsername(name);
		user.setFirstname(name);
		user.setOauth_provider(provider);
		userRepository.save(user);
	}

	

	
}
