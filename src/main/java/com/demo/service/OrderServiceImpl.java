package com.demo.service;

import java.util.Optional;

import com.demo.model.Order;
import com.demo.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;

	@Override
	public Order registerOrder(Order order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public Order findOrderById(long id) {
		Optional<Order> os = orderRepository.findById(id);
		if(os.isEmpty())
			return null;
		else
			return os.get();
	}

}
