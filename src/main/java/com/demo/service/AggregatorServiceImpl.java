package com.demo.service;

import java.util.List;

import com.demo.model.Client;
import com.demo.model.Order;
import com.demo.model.Product;
import com.demo.model.Seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AggregatorServiceImpl implements AggregatorService {
	
	@Autowired
	PersonService personService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderService orderService;

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
	public Product registerProduct(String name, String description, double price, int stock, long seller_id) {
		return productService.registerProduct(name, description, price, stock, seller_id);
	}

	@Override
	public Product findProductById(long id) {
		return productService.findProductById(id);
	}
	
	@Override
	public Order registerOrder(int ammount, long client_id, long product_id, String deliverymechanism, String waytopay) {
		return orderService.registerOrder(ammount, client_id, product_id, deliverymechanism, waytopay);
	}


	@Override
	public Order findOrderById(long id) {
		return orderService.findOrderById(id);
	}
}
