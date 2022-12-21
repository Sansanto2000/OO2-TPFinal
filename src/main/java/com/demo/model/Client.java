package com.demo.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person {

	public Client() {
		// TODO Auto-generated constructor stub
	}

	public Client(String name, String address) {
		super(name, address);
		// TODO Auto-generated constructor stub
	}

}
