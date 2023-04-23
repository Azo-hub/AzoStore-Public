package com.AzoStore001.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.Model.UserShipping;
import com.AzoStore001.Repository.UserShippingRepository;



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
