package com.demo.util.deliverymechanism;

import com.demo.model.Order;

import jakarta.persistence.Entity;

@Entity
public class ToStore implements DeliveryMechanism {

	static ToStore INSTANCE = null;
	
	private ToStore() {}
	
	public static ToStore getInstance() {
		if(INSTANCE == null)
			return new ToStore();
		else
			return INSTANCE;
	}

	@Override
	public double cost(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
