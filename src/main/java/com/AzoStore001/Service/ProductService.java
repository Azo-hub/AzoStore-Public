package com.AzoStore001.Service;

import java.util.List;

import com.AzoStore001.Model.Product;


public interface ProductService {

	Product save(Product product);
	
	List<Product> findAll();

	Product getOne(Long id);
	
	void removeOne(Long id);
	
	List<Product> blurrySearch(String keyword);

	List<Product> findByCategory(String category);

	


}
