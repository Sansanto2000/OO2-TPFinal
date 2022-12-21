package com.demo.util.deliverymechanism;

import com.demo.model.Order;

import jakarta.persistence.Entity;

@Entity
public class ToMailOffice implements DeliveryMechanism {
	
	static ToMailOffice INSTANCE = null;
	
	private ToMailOffice() {}
	
	public static ToMailOffice getInstance() {
		if(INSTANCE == null)
			return new ToMailOffice();
		else
			return INSTANCE;
	}

	@Override
	public double cost(Order order) {
		// TODO Auto-generated method stub
		return 50;
	}

}
