package AzoStore.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.Order;


public interface OrderRepository extends JpaRepository<Order, Long> {
	
	

}
