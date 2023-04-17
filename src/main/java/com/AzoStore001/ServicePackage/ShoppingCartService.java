package com.AzoStore001.ServicePackage;

import AzoStore1.ModelPackage.ShoppingCart;

public interface ShoppingCartService {
	
	ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);
	
	void clearShoppingCart(ShoppingCart shoppingCart);

}
