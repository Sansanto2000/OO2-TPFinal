package com.demo.util.deliverymechanism;

import com.demo.model.Order;
import com.demo.util.Map;

public class ToHome implements DeliveryMechanism {

	static ToHome INSTANCE = null;
	
	private ToHome() {}
	
	public static ToHome getInstance() {
		if(INSTANCE == null)
			return new ToHome();
		else
			return INSTANCE;
	}

	@Override
	public double cost(Order order) {
		// TODO Auto-generated method stub
		return 0.5*Map.getDistanceBetween(1000, 1000);
	}

}
