package com.demo.service;

import java.util.List;

import com.demo.model.Product;

public interface ProductService {
	
	public List<Product> findProducts(String name);
	public Product findProductById(long id);
	public Product registerProduct(String name, String description, double price, int stock, long seller_id);
	
}
