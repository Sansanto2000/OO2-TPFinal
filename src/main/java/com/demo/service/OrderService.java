package com.demo.service;

import com.demo.model.Order;

public interface OrderService {
	
	public Order findOrderById(long id);
	public Order registerOrder(Order order);
	
}
