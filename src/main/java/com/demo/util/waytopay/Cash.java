package com.demo.util.waytopay;

import com.demo.model.Order;

public class Cash implements WayToPay {
	
	static Cash INSTANCE = null;

	private Cash() {}
	
	public static Cash getInstance() {
		if(INSTANCE == null)
			return new Cash();
		else
			return INSTANCE;
	}

	@Override
    public double cost(Order order) {
        // TODO Auto-generated method stub
        return 0;
    }

}
