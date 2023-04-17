package com.AzoStore001.ServicePackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AzoStore1.ModelPackage.UserShipping;
import AzoStore1.RepositoryPackage.UserShippingRepository;



@Service
public class UserShippingServiceImpl implements UserShippingService{
	
	@Autowired
	private UserShippingRepository userShippingRepository;


	@Override
	public UserShipping getOne(Long id) {
		// TODO Auto-generated method stub
		return userShippingRepository.getOne(id);
	}


	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		userShippingRepository.deleteById(id);
		
	}


}
