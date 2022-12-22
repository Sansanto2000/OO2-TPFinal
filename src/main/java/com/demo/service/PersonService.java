package com.demo.service;

import com.demo.model.Client;
import com.demo.model.Seller;

public interface PersonService {
	
	public Seller registerSeller(String name, String address);
    public Seller findSellerById(long id);
    public Seller findSellerByName(String name);
    public Client registerClient(String name, String address);
    public Client findClientById(long id);
    public Client findClientByName(String name);
	
}
