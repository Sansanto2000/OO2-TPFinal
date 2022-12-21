package com.demo.controller.schema;

public class RegisterOrderSchema {

	private int ammount;
	private long client_id;
	private long product_id;
	private String waytopay;
	private String deliverymechanism;
	
	public RegisterOrderSchema() {
		// TODO Auto-generated constructor stub
	}

	public RegisterOrderSchema(int ammount, long client_id, long product_id, String waytopay, String deliverymechanism) {
		super();
		this.ammount = ammount;
		this.client_id = client_id;
		this.product_id = product_id;
		this.waytopay = waytopay;
		this.deliverymechanism = deliverymechanism;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public long getClient_id() {
		return client_id;
	}

	public void setClient_id(long client_id) {
		this.client_id = client_id;
	}

	public long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	public String getWaytopay() {
		return waytopay;
	}

	public void setWaytopay(String waytopay) {
		this.waytopay = waytopay;
	}

	public String getDeliverymechanism() {
		return deliverymechanism;
	}

	public void setDeliverymechanism(String deliverymechanism) {
		this.deliverymechanism = deliverymechanism;
	}

}
