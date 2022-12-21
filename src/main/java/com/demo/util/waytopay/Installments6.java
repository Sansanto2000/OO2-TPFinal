package com.demo.util.waytopay;

import com.demo.model.Order;

public class Installments6 implements WayToPay{
	
	static Installments6 INSTANCE = null;

	private Installments6() {}
	
	public static Installments6 getInstance() {
		if(INSTANCE == null)
			return new Installments6();
		else
			return INSTANCE;
	}

	@Override
    public double cost(Order order) {
        // TODO Auto-generated method stub
        return 0.2;
    }

}