package com.demo.controller.schema;

public class RegisterSellerSchema {

	private String name;
	private String address;
	
	public RegisterSellerSchema() {
	}
	
	public RegisterSellerSchema(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
