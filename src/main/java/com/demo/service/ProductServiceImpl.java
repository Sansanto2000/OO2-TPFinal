package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.model.Product;
import com.demo.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findProducts(String name) {
		List<Product> list = productRepository.findAllByName(name);
		if(list == null)
			list = new ArrayList<Product>();
		return list;
	}

	@Override
	public Product registerProduct(Product product) {
		productRepository.save(product);
		return product;
	}
	
	@Override
	public Product updateProduct(Product product) {
		productRepository.save(product);
		return product;
	}

	@Override
	public Product findProductById(long id) {
		Optional<Product> op = productRepository.findById(id);
		if(op.isEmpty())
			return null;
		else
			return op.get();
	}

}
