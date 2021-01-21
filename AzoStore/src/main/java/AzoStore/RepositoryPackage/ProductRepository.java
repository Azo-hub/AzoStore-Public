package AzoStore.RepositoryPackage;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import AzoStore.ModelPackage.Product;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {

	List<Product> findByCategory(String category);

	List<Product> findByNameContaining(String keyword);



}
