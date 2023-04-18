package com.AzoStore001.ServicePackage;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.ModelPackage.CartItem;
import com.AzoStore001.ModelPackage.ShoppingCart;
import com.AzoStore001.RepositoryPackage.ShoppingCartRepository;



@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{

	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	
	@Override
	public ShoppingCart updateShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		BigDecimal cartTotal = new BigDecimal(0);
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for(CartItem cartItem : cartItemList) {
			
			if (cartItem.getProduct().getInStockNumber() > 0) {
				cartItemService.updateCartItem(cartItem);
				cartTotal = cartTotal.add(cartItem.getSubtotal());
				
				
			}
		}
		
		
		shoppingCart.setGrandTotal(cartTotal);
		
		shoppingCartRepository.save(shoppingCart);
		
		return shoppingCart;
		
		
	}


	@Override
	public void clearShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			cartItem.setShoppingCart(null);
			cartItemService.save(cartItem);
		}
		
		shoppingCart.setGrandTotal(new BigDecimal(0));
		
		shoppingCartRepository.save(shoppingCart);
		
	}
	
	

}
