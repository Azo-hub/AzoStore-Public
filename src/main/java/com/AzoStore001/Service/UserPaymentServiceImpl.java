package com.AzoStore001.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.Model.UserPayment;
import com.AzoStore001.Repository.UserPaymentRepository;


@Service
public class UserPaymentServiceImpl implements UserPaymentService{
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;


	@Override
	public UserPayment getOne(Long id) {
		// TODO Auto-generated method stub
		return userPaymentRepository.getOne(id);
	}


	@Override
	public void removeById(Long id) {
		// TODO Auto-generated method stub
		userPaymentRepository.deleteById(id);
		
	}

}

