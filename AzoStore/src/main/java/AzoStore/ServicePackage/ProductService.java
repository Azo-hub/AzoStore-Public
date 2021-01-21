package AzoStore.ServicePackage;

import java.util.List;
import java.util.Optional;

import AzoStore.ModelPackage.Product;


public interface ProductService {

	Product save(Product product);
	
	List<Product> findAll();

	Product getOne(Long id);
	
	void removeOne(Long id);
	
	List<Product> blurrySearch(String keyword);

	List<Product> findByCategory(String category);

	


}
