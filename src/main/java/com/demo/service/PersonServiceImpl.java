package com.demo.service;

import java.util.Optional;

import com.demo.model.Client;
import com.demo.model.Seller;
import com.demo.repository.ClientRepository;
import com.demo.repository.SellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
	
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	SellerRepository sellerRepository;

	@Override
	public Seller registerSeller(String name, String address) {
		Optional<Seller> os = sellerRepository.findByName(name);
		Seller s;
		if(os.isEmpty()) {
			s = new Seller(name, address);
			sellerRepository.save(s);
		} else {
			s = null;
		}
		return s;
	}

	@Override
	public Client registerClient(String name, String address) {
		Optional<Client> oc = clientRepository.findByName(name);
		Client c;
		if(oc.isEmpty()) {
			c = new Client(name, address);
			clientRepository.save(c);
		} else {
			c = null;  
		}
		return c;
	}

	@Override
	public Seller findSellerByName(String name) {
		Optional<Seller> os = sellerRepository.findByName(name);
		if(os.isEmpty())
			return null;
		else
			return os.get();
	}
	
	@Override
	public Seller findSellerById(long id) {
		Optional<Seller> os = sellerRepository.findById(id);
		if(os.isEmpty())
			return null;
		else
			return os.get();
	}

	@Override
	public Client findClientByName(String name) {
		Optional<Client> oc = clientRepository.findByName(name);
		if(oc.isEmpty())
			return null;
		else
			return oc.get();
	}
	
	@Override
	public Client findClientById(long id) {
		Optional<Client> oc = clientRepository.findById(id);
		if(oc.isEmpty())
			return null;
		else
			return oc.get();
	}

}
