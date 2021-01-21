package AzoStore.ServicePackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AzoStore.ModelPackage.UserShipping;
import AzoStore.RepositoryPackage.UserShippingRepository;


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
