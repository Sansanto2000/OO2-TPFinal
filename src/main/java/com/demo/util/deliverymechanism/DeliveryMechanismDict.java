package com.demo.util.deliverymechanism;

import java.util.HashMap;

public class DeliveryMechanismDict {

	private DeliveryMechanismDict() {}
	
	static HashMap<String, DeliveryMechanism> dict = new HashMap<String, DeliveryMechanism>();
	static {
		dict.put("toHome", ToHome.getInstance());
		dict.put("toMailOffice", ToMailOffice.getInstance());
		dict.put("toStore", ToStore.getInstance());
	}
	
	public static DeliveryMechanism get(String key) {
	    return dict.get(key);
	}
	
	public static boolean containsKey(String key) {
	    return dict.containsKey(key);
	}

}
