package com.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.demo.controller.schema.RegisterClientSchema;
import com.demo.controller.schema.RegisterOrderSchema;
import com.demo.controller.schema.RegisterProductSchema;
import com.demo.controller.schema.RegisterSellerSchema;
import com.demo.model.Client;
import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.model.Order;
import com.demo.service.ProxyService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
@RestController
@RequestMapping("/api")
public class API {
	
	@Autowired
	ProxyService proxyService;
	
	// Endpoints para persons
	@PostMapping("/seller")
	public ResponseEntity<Seller> registerSeller(@RequestBody RegisterSellerSchema request) {
		Seller s = proxyService.registerSeller(request.getName(), request.getAddress());
		if(s == null)
			return new ResponseEntity<Seller>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Seller>(s, HttpStatus.CREATED);
	}
	
	@GetMapping("/seller")
    public ResponseEntity<Seller> findSeller(@RequestParam String name) {
		Seller s = proxyService.findSellerByName(name);
		if(s == null)
			return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Seller>(s, HttpStatus.OK);
	}
	
	@PostMapping("/client")
    public ResponseEntity<Client> registerClient(@RequestBody RegisterClientSchema request) {
		Client c = proxyService.registerClient(request.getName(), request.getAddress());
		if(c == null)
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Client>(c, HttpStatus.CREATED);
	}
	
	@GetMapping("/client")
    public ResponseEntity<Client> findClient(@RequestParam String name) {
		Client c = proxyService.findClientByName(name);
		if(c == null)
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<Client>(c, HttpStatus.OK);
	}
	
	// Endpoints para products
	@PostMapping("/product")
	public ResponseEntity<Product> registerProduct(@RequestBody RegisterProductSchema request) {
		Product p = proxyService.registerProduct(request.getName(), request.getDescription(), 
				request.getPrice(), request.getStock(), request.getSeller_id());
		if(p == null) 
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Product>(p, HttpStatus.CREATED);
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> findProducts(@RequestParam String name) {
		List<Product> list = proxyService.findProducts(name);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/order")
    public ResponseEntity<Order> registerOrder(@RequestBody RegisterOrderSchema request) {
		Order o = proxyService.registerOrder(request.getAmmount(), request.getClient_id(), 
				request.getProduct_id(), request.getDeliverymechanism(), request.getWaytopay());
		if(o == null)
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Order>(o, HttpStatus.OK);
    }
	
	@GetMapping("order/calculateTotalCost")
    public ResponseEntity<Double> calculateOrderTotalCost(@RequestParam long orderId) {
		Order o = proxyService.findOrderById(orderId);
		if(o == null)
			return new ResponseEntity<Double>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Double>(o.calculateTotalCost(), HttpStatus.OK);
    }

}
