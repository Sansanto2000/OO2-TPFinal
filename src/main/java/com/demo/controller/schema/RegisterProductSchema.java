package com.demo.controller.schema;

public class RegisterProductSchema {

	private String name;
	private String description;
	private double price;
	private int stock;
	private long seller_id;
	
	public RegisterProductSchema() {
		// TODO Auto-generated constructor stub
	}

	public RegisterProductSchema(String name, String description, double price, int stock, long seller_id) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.seller_id = seller_id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public long getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(long seller_id) {
		this.seller_id = seller_id;
	}

}
