package AzoStore.ServicePackage;

import AzoStore.ModelPackage.BillingAddress;
import AzoStore.ModelPackage.Order;
import AzoStore.ModelPackage.Payment;
import AzoStore.ModelPackage.ShippingAddress;
import AzoStore.ModelPackage.ShoppingCart;
import AzoStore.ModelPackage.User;

public interface OrderService {
	Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress,
			BillingAddress billingAddress, Payment payment, String shippingMethod, User user);
	
	Order getOne(Long id);

}
