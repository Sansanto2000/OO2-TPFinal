package com.demo.service;

import com.demo.model.Order;

public interface OrderService {
	
	public Order findOrderById(long id);
	public Order registerOrder(int ammount, long client_id, long product_id, 
			String deliverymechanism, String waytopay);
	
}
