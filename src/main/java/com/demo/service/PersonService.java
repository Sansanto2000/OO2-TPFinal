package com.demo.service;

import com.demo.model.Person;

public interface PersonService {
	
	public Person registerSeller(String name, String address);
    public Person registerClient(String name, String address);
    public Person findSellerById(long id);
    public Person findSellerByName(String name);
    public Person findClientById(long id);
    public Person findClientByName(String name);
	
}
