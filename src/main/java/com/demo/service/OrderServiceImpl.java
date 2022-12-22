package com.demo.service;

import java.util.Optional;

import com.demo.model.Client;
import com.demo.model.Order;
import com.demo.model.Product;
import com.demo.repository.ClientRepository;
import com.demo.repository.OrderRepository;
import com.demo.repository.ProductRepository;
import com.demo.util.deliverymechanism.DeliveryMechanismDict;
import com.demo.util.waytopay.WayToPayDict;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ProductRepository productRepository;

	@Override
	public Order findOrderById(long id) {
		Optional<Order> os = orderRepository.findById(id);
		if(os.isEmpty())
			return null;
		return os.get();
	}

	@Override
	public Order registerOrder(int ammount, long client_id, long product_id, String deliverymechanism, String waytopay) {
		//Comprueba que en la orden que se intenta crear la cantidad sea por lo menos 1 
		if(ammount < 1)
			return null;
		//Comprueba existencia del cliente
		Optional<Client> oc = clientRepository.findById(client_id); 
		if(oc.isEmpty())
			return null;
		//Comprueba existencia del producto
		Optional<Product> op =productRepository.findById(product_id);
		if(op.isEmpty())
			return null;
		//Comprueba existencia del metodo de pago elegido
		if(!WayToPayDict.containsKey(waytopay))
			return null;
		//Comprueba existencia del mecanismo de entrega elegido
		if(!DeliveryMechanismDict.containsKey(deliverymechanism))
			return null;
		//Comprueba disponibilidad de stock, en caso de que haya entonces lo actualiza
		Product p = op.get();
		if(p.getStock() < ammount)
			return null;
		p.setStock(p.getStock()-ammount);
		//Genera y guarda la orden
		Order o = new Order(ammount, oc.get(), p, deliverymechanism, waytopay);
		orderRepository.save(o);
		return o;
	}

}
