package com.demo.service;

import java.util.List;

import com.demo.model.Client;
import com.demo.model.Order;
import com.demo.model.Product;
import com.demo.model.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyServiceImpl implements ProxyService {
	
	@Autowired
	PersonServiceImpl personService;
	@Autowired
	ProductServiceImpl productService;
	@Autowired
	OrderServiceImpl orderService;

	@Override
	public Seller registerSeller(String name, String address) {
		return personService.registerSeller(name, address);
	}

	@Override
	public Client registerClient(String name, String address) {
		return personService.registerClient(name, address);
	}

	@Override
	public Seller findSellerByName(String name) {
		return personService.findSellerByName(name);
	}
	
	@Override
	public Seller findSellerById(long id) {
		return personService.findSellerById(id);
	}

	@Override
	public Client findClientByName(String name) {
		return personService.findClientByName(name);
	}
	
	@Override
	public Client findClientById(long id) {
		return personService.findClientById(id);
	}

	@Override
	public List<Product> findProducts(String name) {
		return productService.findProducts(name);
	}

	@Override
	public Product registerProduct(Product product) {
		return productService.registerProduct(product);
	}
	
	@Override
	public Product updateProduct(Product product) {
		return productService.updateProduct(product);
	}

	@Override
	public Product findProductById(long id) {
		return productService.findProductById(id);
	}

	@Override
	public Order registerOrder(Order order) {
		return orderService.registerOrder(order);
	}

	@Override
	public Order findOrderById(long id) {
		return orderService.findOrderById(id);
	}

}