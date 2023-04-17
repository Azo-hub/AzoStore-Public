package com.AzoStore001.ServicePackage;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AzoStore1.ModelPackage.BillingAddress;
import AzoStore1.ModelPackage.CartItem;
import AzoStore1.ModelPackage.Order;
import AzoStore1.ModelPackage.Payment;
import AzoStore1.ModelPackage.Product;
import AzoStore1.ModelPackage.ShippingAddress;
import AzoStore1.ModelPackage.ShoppingCart;
import AzoStore1.ModelPackage.User;
import AzoStore1.RepositoryPackage.OrderRepository;




@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartItemService cartItemService;

/*	@Override
	public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress,
			Payment payment, String shippingMethod, User user) {
		// TODO Auto-generated method stub
		Order order = new Order();
		order.setBillingAddress(billingAddress);
		order.setOrderStatus("created");
		order.setPayment(payment);
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
		billingAddress.setOrder(order);
		payment.setOrder(order);
		order.setUser(user);
		order = orderRepository.save(order);
		
		return order;
	}
	
	*/
	
	
	
	
	
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
