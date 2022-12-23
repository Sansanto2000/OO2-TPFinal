package com.demo.finaloo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.demo.model.Client;
import com.demo.model.Order;
import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.service.ProxyService;
import com.demo.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@Sql(scripts = {"./rebootDB.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ProxyServiceTest extends Finaloo2ApplicationTests {

	@Autowired
	ProxyService proxyService;
	
	Seller seller_aux;
	Client client_aux;
	Product product_aux;
	Order order_aux;
	
	@BeforeEach
	public void setup() {
		seller_aux = proxyService.registerSeller("Nikola", "Europa, Checa, Praga");
		client_aux = proxyService.registerClient("Aristóteles", "Estagira, Reino de Macedonia");
		product_aux = proxyService.registerProduct("Televisor", "52 pulgadas", 5000.00, 200, 1);
		order_aux = proxyService.registerOrder(4, 1, 1, "toHome", "cash");
		
	
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
	
	@Test
	public void testRegisterProduct() {
		Product product = proxyService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		assertNotEquals(product, null);
	}
	
	@Test
	public void testRegisterProductNotSeller() {
		Product product = proxyService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), 25);
		assertNull(product);
	}
	
	@Test
	public void testRegistresProductStockMinus0() {
		Product product = proxyService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), -30, 1);
		assertNull(product);
	}
	
	@Test 
	public void testFindProducts() {
		proxyService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		proxyService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		proxyService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		List<Product> products = proxyService.findProducts("Televisor");
		assertEquals(products.size(), 4);	
	}
	
	@Test
	public void testFindProductsWithoutMatches() {
		List<Product> products = proxyService.findProducts("Estenolotenes");
		assertEquals(products.size(), 0);	
	}
	
	@Test
	public void testRegisterOrder() {
		Order order = proxyService.registerOrder(2, 1, 1, "toHome", "cash");
		assertNull(order);
		Order order_find= proxyService.findOrderById(1);
		assertEquals(order, order_find); 
	}
	
	
	@Test
	public void testRegistrarOrderMayorAmmount() {
		Order order = proxyService.registerOrder(50, 1, 1, "toHome", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleSeller() {
		Order order = proxyService.registerOrder(1, 24, 1, "toHome", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleProduct() {
		Order order = proxyService.registerOrder(1, 24, 1, "toHome", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleDM() {
		Order order = proxyService.registerOrder(1, 1, 1, "Ricardito", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleWTP() {
		Order order = proxyService.registerOrder(1, 1, 1, "toHome", "Ricardito");
		assertEquals(order, null);
	}
	
	@Test
	public void testCalcularTotalCostToMailOfficeWithCash() {
		Order order = proxyService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toMailOffice", "cash");
		double price = order.calculateTotalCost();
		assertEquals(price, 5050.00);
	}
	
	@Test
	public void testCalcularTotalCostToMailOfficeWith6Installments() {
		Order order = proxyService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toMailOffice", "installments6");
		double price = order.calculateTotalCost();
		assertEquals(price, 6060.00);
	}
	
	@Test
	public void testCalcularTotalCostToStoreWitchCash() {
		Order order = proxyService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toStore", "cash");
		double price = order.calculateTotalCost();
		assertEquals(price, 5000.00);
	}
	
	@Test
	public void testCalcularTotalCostToStoreWith6Installments() {
		Order order = proxyService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toStore", "installments6");
		double price = order.calculateTotalCost();
		assertEquals(price, 6000.00);
	}

	@Test
	public void testCalcularTotalCostToHomeWithCash() {
		Order order = proxyService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toHome", "cash");
		double price = order.calculateTotalCost();
		assertEquals(price, 6000.00 );
		
	}
	
	@Test
	public void testCalcularTotalCostToHomeWitch6Installments() {
		Order order = proxyService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toHome", "installments6");
		double price = order.calculateTotalCost();
		assertEquals(price, 7200.00 );
	}
	
	

	
	
	
	
	
	
	


}