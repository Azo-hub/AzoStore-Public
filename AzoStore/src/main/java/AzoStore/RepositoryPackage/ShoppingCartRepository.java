package AzoStore.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository <ShoppingCart, Long> {

	
}
