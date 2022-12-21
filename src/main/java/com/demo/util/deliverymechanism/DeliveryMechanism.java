package com.demo.util.deliverymechanism;

import com.demo.model.Order;

public interface DeliveryMechanism {
	
	public double cost(Order order);
	
}
