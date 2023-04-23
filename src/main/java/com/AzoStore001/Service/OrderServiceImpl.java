package com.AzoStore001.Service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AzoStore001.Model.CartItem;
import com.AzoStore001.Model.Order;
import com.AzoStore001.Model.Product;
import com.AzoStore001.Model.ShippingAddress;
import com.AzoStore001.Model.ShoppingCart;
import com.AzoStore001.Model.User;
import com.AzoStore001.Repository.OrderRepository;




@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;

	
	@Override
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, 
		 String shippingMethod, User user) {
		// TODO Auto-generated method stub
		Order order = new Order();
		
		order.setOrderStatus("created");
		
		order.setShippingAddress(shippingAddress);
		order.setShippingMethod(shippingMethod);
		
		
		
		List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
		
		for (CartItem cartItem : cartItemList) {
			
			Product product = cartItem.getProduct();
			cartItem.setOrder(order);
			product.setInStockNumber(product.getInStockNumber() - cartItem.getQty());
			
		}
		
		order.setCartItemList(cartItemList);
		order.setOrderDate(Calendar.getInstance().getTime());
		order.setOrderTotal(shoppingCart.getGrandTotal());
		shippingAddress.setOrder(order);
		
		
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}

	
	
	

	@Override
	public Order getOne(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.getOne(id);
	}
	
	

}
