package com.AzoStore001.ServicePackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.ModelPackage.PasswordResetToken;
import com.AzoStore001.ModelPackage.ShoppingCart;
import com.AzoStore001.ModelPackage.User;
import com.AzoStore001.ModelPackage.UserBilling;
import com.AzoStore001.ModelPackage.UserPayment;
import com.AzoStore001.ModelPackage.UserRole;
import com.AzoStore001.ModelPackage.UserShipping;
import com.AzoStore001.RepositoryPackage.PasswordResetTokenRepository;
import com.AzoStore001.RepositoryPackage.RoleRepository;
import com.AzoStore001.RepositoryPackage.UserPaymentRepository;
import com.AzoStore001.RepositoryPackage.UserRepository;
import com.AzoStore001.RepositoryPackage.UserShippingRepository;



@Service
public class UserServiceImpl implements UserService {

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

	

	

	
}
