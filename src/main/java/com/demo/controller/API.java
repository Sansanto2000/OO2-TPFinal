package com.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.demo.controller.schema.RegisterOrderSchema;
import com.demo.controller.schema.RegisterProductSchema;
import com.demo.model.Client;
import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.model.Order;
import com.demo.service.ProxyServiceImpl;
import com.demo.util.deliverymechanism.DeliveryMechanismDict;
import com.demo.util.waytopay.WayToPayDict;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class API {
	
	@Autowired
	ProxyServiceImpl proxyService;
	
	// Endpoints para persons
	@PostMapping("/seller")
	public ResponseEntity<Seller> registerSeller(@RequestParam String name, @RequestParam String address) {
		Seller s = proxyService.registerSeller(name, address);
		if(s == null)
			return new ResponseEntity<Seller>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Seller>(s, HttpStatus.CREATED);
	}
	
	@GetMapping("/seller")
    public ResponseEntity<Seller> FindSeller(@RequestParam String name) {
		Seller s = proxyService.findSellerByName(name);
		if(s == null)
			return new ResponseEntity<Seller>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Seller>(s, HttpStatus.OK);
	}
	
	@PostMapping("/client")
    public ResponseEntity<Client> registerClient(@RequestParam String name, @RequestParam String address) {
		Client c = proxyService.registerClient(name, address);
		if(c == null)
			return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Client>(c, HttpStatus.CREATED);
	}
	
	@GetMapping("/client")
    public ResponseEntity<Client> FindClient(@RequestParam String name) {
		Client c = proxyService.findClientByName(name);
		if(c == null)
			return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
    	return new ResponseEntity<Client>(c, HttpStatus.OK);
	}
	
	// Endpoints para products
	@PostMapping("/product")
	public ResponseEntity<Product> registerProduct(@RequestBody RegisterProductSchema rps) {
		Seller pe = proxyService.findSellerById(rps.getSeller_id());
		if(pe == null) 
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		else {
			Product pr = new Product(rps.getName(), rps.getDescription(), rps.getPrice(), rps.getStock(), pe);
			proxyService.registerProduct(pr);
			return new ResponseEntity<Product>(pr, HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> findProducts(@RequestParam String name) {
		List<Product> list = proxyService.findProducts(name);
		return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
	}
	
	
	@PostMapping("/order")
    public ResponseEntity<Order> registerOrder(@RequestBody RegisterOrderSchema ros) {
		//Comprueba existencia del cliente
		Client c = proxyService.findClientById(ros.getClient_id());
		if(c == null)
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		//Comprueba existencia del producto
		Product p = proxyService.findProductById(ros.getProduct_id());
		if(p == null)
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		//Comprueba existencia del metodo de pago elegido
		if(!WayToPayDict.containsKey(ros.getWaytopay()))
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		//Comprueba existencia del mecanismo de entrega elegido
		if(!DeliveryMechanismDict.containsKey(ros.getDeliverymechanism()))
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		//Comprueba disponibilidad de stock, en caso de que haya entonces lo actualiza
		if(p.getStock() < ros.getAmmount())
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		p.setStock(p.getStock()-ros.getAmmount());
		//Genera y guarda la orden
		Order o = new Order(ros.getAmmount(), c, p, ros.getDeliverymechanism(), ros.getWaytopay());
		Order resp = proxyService.registerOrder(o);
		if(resp == null)
			return new ResponseEntity<Order>(HttpStatus.BAD_REQUEST);
		//Guarda el producto acutualizado
		proxyService.updateProduct(p);
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
