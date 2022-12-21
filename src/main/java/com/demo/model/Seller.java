package com.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Seller extends Person {

	public Seller() {
		// TODO Auto-generated constructor stub
	}

	public Seller(String name, String address) {
		super(name, address);
		// TODO Auto-generated constructor stub
	}

}
