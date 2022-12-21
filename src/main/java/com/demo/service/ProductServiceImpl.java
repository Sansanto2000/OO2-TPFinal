package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.repository.ProductRepository;
import com.demo.repository.SellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	SellerRepository sellerRepository;

	@Override
	public List<Product> findProducts(String name) {
		List<Product> list = productRepository.findAllByName(name);
		if(list == null)
			list = new ArrayList<Product>();
		return list;
	}
	
	@Override
	public Product registerProduct(String name, String description, double price, int stock, long seller_id) {
		//Comprueba que el stock indicado para el producto no sea negativo
		if(stock < 0)
			return null;
		//Comprueba existencia del vendedor
		Optional<Seller> os = sellerRepository.findById(seller_id);
		if(os.isEmpty()) 
			return null;
		//Genera y guarda el producto
		Product p = new Product(name, description, price, stock, os.get());
		productRepository.save(p);
		return p;
	}

	@Override
	public Product findProductById(long id) {
		Optional<Product> op = productRepository.findById(id);
		if(op.isEmpty())
			return null;
		return op.get();
	}

}
