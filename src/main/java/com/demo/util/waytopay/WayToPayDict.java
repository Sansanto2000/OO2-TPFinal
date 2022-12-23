package com.demo.util.waytopay;

import java.util.HashMap;

public class WayToPayDict {

	private WayToPayDict() {}
	
	static HashMap<String, WayToPay> dict = new HashMap<String, WayToPay>();
	static {
		dict.put("cash", Cash.getInstance());
		dict.put("installments6", Installments6.getInstance());
	}
	
	public static WayToPay get(String key) {
	    return dict.get(key);
	}
	
	public static boolean containsKey(String key) {
	    return dict.containsKey(key);
	}

}
