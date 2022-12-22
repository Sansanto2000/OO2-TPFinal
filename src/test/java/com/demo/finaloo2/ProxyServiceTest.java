package com.demo.finaloo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.demo.model.Client;
import com.demo.model.Seller;
import com.demo.service.ProxyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@Sql(scripts = {"./rebootDB.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ProxyServiceTest extends Finaloo2ApplicationTests {

	@Autowired
	ProxyService proxyService;
	
	Seller seller_aux;
	Client client_aux;
	
	@BeforeEach
	public void setup() {
		seller_aux = proxyService.registerSeller("Nikola", "Europa, Checa, Praga");
		client_aux = proxyService.registerClient("Aristóteles", "Estagira, Reino de Macedonia");
	}
	
	@Test
	public void testRegisterSeller() {
		Seller seller = proxyService.registerSeller(seller_aux.getName(), seller_aux.getAddress());
		assertNull(seller);
		seller = proxyService.registerSeller("Galileo", "Italia, Toscana, Basílica de la Santa Cruz");
		Seller seller_find = proxyService.findSellerByName("Galileo");
		assertEquals(seller, seller_find);
	}
	
	@Test
	public void testFindSeller() {
		Seller seller = proxyService.findSellerByName("Galileo");
		assertNull(seller);
		seller = proxyService.findSellerByName("Nikola");
		assertEquals(seller, seller_aux);
	}
	
	@Test
	public void testRegisterClient() {
		Client client = proxyService.registerClient(client_aux.getName(), client_aux.getAddress());
		assertNull(client);
		client = proxyService.registerClient("Nicodemo", "Judea, Israel");
		Client client_find = proxyService.findClientByName("Nicodemo");
		assertEquals(client, client_find);
	}
	
	@Test
	public void testFindClient() {
		Client client = proxyService.findClientByName("Nicodemo");
		assertNull(client);
		client = proxyService.findClientByName("Aristóteles");
		assertEquals(client, client_aux);
	}

}