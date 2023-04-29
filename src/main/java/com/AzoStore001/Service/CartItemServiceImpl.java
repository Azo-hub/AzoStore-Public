package com.AzoStore001.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.Model.CartItem;
import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ProductToCartItem;
import com.AzoStore001.Model.ShoppingCart;
import com.AzoStore001.Model.User;
import com.AzoStore001.Repository.CartItemRepository;
import com.AzoStore001.Repository.ProductToCartItemRepository;


@Service
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	@Autowired
	private ProductToCartItemRepository productToCartItemRepository;
	

	@Override
	public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByShoppingCart(shoppingCart);
	}

	@SuppressWarnings("deprecation")
	@Override
	public CartItem updateCartItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		
		BigDecimal bigDecimal = new BigDecimal(cartItem.getProduct().getOurPrice()).multiply(new BigDecimal(cartItem.getQty()));
		
		bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		cartItem.setSubtotal(bigDecimal);
		
		cartItemRepository.save(cartItem);
		
		return cartItem;
		
	}

	@Override
	public CartItem addProductToCartItem(Product product, User user, int qty) {
		// TODO Auto-generated method stub
		
		List<CartItem> cartItemList = findByShoppingCart(user.getShoppingCart());
		
		for (CartItem cartItem : cartItemList) {
			if (product.getId() == cartItem.getProduct().getId()) {
				cartItem.setQty(cartItem.getQty()+qty);
				cartItem.setSubtotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal (qty)));
				cartItemRepository.save(cartItem);
				
				return cartItem;
				
			}
			
		}
		
		
		CartItem cartItem = new CartItem();
		cartItem.setShoppingCart(user.getShoppingCart());
		cartItem.setProduct(product);
		
		cartItem.setQty(qty);
		cartItem.setSubtotal(new BigDecimal(product.getOurPrice()).multiply(new BigDecimal (qty)));
		cartItem = cartItemRepository.save(cartItem);
		
		ProductToCartItem productToCartItem = new ProductToCartItem();
		productToCartItem.setProduct(product);
		productToCartItem.setCartItem(cartItem);
		productToCartItemRepository.save(productToCartItem);
		
		return cartItem;
	}

	@Override
	public CartItem save(CartItem cartItem) {
		// TODO Auto-generated method stub
		return cartItemRepository.save(cartItem);
	}


	@Override
	public CartItem findById(Long id) {
		// TODO Auto-generated method stub
		return cartItemRepository.getOne(id);
	}

	@Override
	public void removeCartItem(Long id) {
		// TODO Auto-generated method stub
		//productToCartItemRepository.deleteByCartItem(cartItem);
		cartItemRepository.deleteById(id);
		
	}
	
	@Override
	public void removeproductToCartItem(Long id) {
		
		productToCartItemRepository.deleteById(id);
		
	}

	
	
	
	
	
	@Override
	public List<CartItem> findByOrder(Order order) {
		// TODO Auto-generated method stub
		return cartItemRepository.findByOrder(order);
	}
	
	

}
