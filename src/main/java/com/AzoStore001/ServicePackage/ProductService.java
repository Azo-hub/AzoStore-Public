package com.AzoStore001.ServicePackage;

import java.util.List;

import com.AzoStore001.ModelPackage.Product;




public interface ProductService {

	Product save(Product product);
	
	List<Product> findAll();

	Product getOne(Long id);
	
	void removeOne(Long id);
	
	List<Product> blurrySearch(String keyword);

	List<Product> findByCategory(String category);

	


}
