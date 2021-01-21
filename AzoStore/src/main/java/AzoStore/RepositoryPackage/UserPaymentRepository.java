package AzoStore.RepositoryPackage;

import org.springframework.data.jpa.repository.JpaRepository;

import AzoStore.ModelPackage.UserPayment;


public interface UserPaymentRepository extends JpaRepository <UserPayment, Long> {
	

}
