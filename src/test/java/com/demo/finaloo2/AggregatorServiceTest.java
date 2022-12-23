package com.demo.finaloo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.demo.model.Client;
import com.demo.model.Order;
import com.demo.model.Product;
import com.demo.model.Seller;
import com.demo.service.AggregatorService;
import com.demo.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@Sql(scripts = {"./rebootDB.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AggregatorServiceTest extends Finaloo2ApplicationTests {

	@Autowired
	AggregatorService aggregatorService;
	
	Seller seller_aux;
	Client client_aux;
	Product product_aux;
	Order order_aux;
	
	@BeforeEach
	public void setup() {
		seller_aux = aggregatorService.registerSeller("Nikola", "Europa, Checa, Praga");
		client_aux = aggregatorService.registerClient("Aristóteles", "Estagira, Reino de Macedonia");
		product_aux = aggregatorService.registerProduct("Televisor", "52 pulgadas", 5000.00, 200, 1);
		order_aux = aggregatorService.registerOrder(4, 1, 1, "toHome", "cash");
		
	
	}
	
	@Test
	public void testRegisterSeller() {
		Seller seller = aggregatorService.registerSeller(seller_aux.getName(), seller_aux.getAddress());
		assertNull(seller);
		seller = aggregatorService.registerSeller("Galileo", "Italia, Toscana, Basílica de la Santa Cruz");
		Seller seller_find = aggregatorService.findSellerByName("Galileo");
		assertEquals(seller, seller_find);
	}
	
	@Test
	public void testFindSeller() {
		Seller seller = aggregatorService.findSellerByName("Galileo");
		assertNull(seller);
		seller = aggregatorService.findSellerByName("Nikola");
		assertEquals(seller, seller_aux);
	}
	
	@Test
	public void testRegisterClient() {
		Client client = aggregatorService.registerClient(client_aux.getName(), client_aux.getAddress());
		assertNull(client);
		client = aggregatorService.registerClient("Nicodemo", "Judea, Israel");
		Client client_find = aggregatorService.findClientByName("Nicodemo");
		assertEquals(client, client_find);
	}
	
	@Test
	public void testFindClient() {
		Client client = aggregatorService.findClientByName("Nicodemo");
		assertNull(client);
		client = aggregatorService.findClientByName("Aristóteles");
		assertEquals(client, client_aux);
	}
	
	@Test
	public void testRegisterProduct() {
		Product product = aggregatorService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		assertNotEquals(product, null);
	}
	
	@Test
	public void testRegisterProductNotSeller() {
		Product product = aggregatorService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), 25);
		assertNull(product);
	}
	
	@Test
	public void testRegistresProductStockMinus0() {
		Product product = aggregatorService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), -30, 1);
		assertNull(product);
	}
	
	@Test 
	public void testFindProducts() {
		aggregatorService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		aggregatorService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		aggregatorService.registerProduct(product_aux.getName(), product_aux.getDescription(), product_aux.getPrice(), product_aux.getStock(), product_aux.getSeller().getId());
		List<Product> products = aggregatorService.findProducts("Televisor");
		assertEquals(products.size(), 4);	
	}
	
	@Test
	public void testFindProductsWithoutMatches() {
		List<Product> products = aggregatorService.findProducts("Estenolotenes");
		assertEquals(products.size(), 0);	
	}
	
	@Test
	public void testRegisterOrder() {
		Order order = aggregatorService.registerOrder(2, 1, 1, "toHome", "cash");
		assertNull(order);
		Order order_find= aggregatorService.findOrderById(1);
		assertEquals(order, order_find); 
	}
	
	
	@Test
	public void testRegistrarOrderMayorAmmount() {
		Order order = aggregatorService.registerOrder(50, 1, 1, "toHome", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleSeller() {
		Order order = aggregatorService.registerOrder(1, 24, 1, "toHome", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleProduct() {
		Order order = aggregatorService.registerOrder(1, 24, 1, "toHome", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleDM() {
		Order order = aggregatorService.registerOrder(1, 1, 1, "Ricardito", "cash");
		assertEquals(order, null);
	}
	
	@Test
	public void testRegistrarOrderIncopatibleWTP() {
		Order order = aggregatorService.registerOrder(1, 1, 1, "toHome", "Ricardito");
		assertEquals(order, null);
	}
	
	@Test
	public void testCalcularTotalCostToMailOfficeWithCash() {
		Order order = aggregatorService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toMailOffice", "cash");
		double price = order.calculateTotalCost();
		assertEquals(price, 5050.00);
	}
	
	@Test
	public void testCalcularTotalCostToMailOfficeWith6Installments() {
		Order order = aggregatorService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toMailOffice", "installments6");
		double price = order.calculateTotalCost();
		assertEquals(price, 6060.00);
	}
	
	@Test
	public void testCalcularTotalCostToStoreWitchCash() {
		Order order = aggregatorService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toStore", "cash");
		double price = order.calculateTotalCost();
		assertEquals(price, 5000.00);
	}
	
	@Test
	public void testCalcularTotalCostToStoreWith6Installments() {
		Order order = aggregatorService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toStore", "installments6");
		double price = order.calculateTotalCost();
		assertEquals(price, 6000.00);
	}

	@Test
	public void testCalcularTotalCostToHomeWithCash() {
		Order order = aggregatorService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toHome", "cash");
		double price = order.calculateTotalCost();
		assertEquals(price, 6000.00 );
		
	}
	
	@Test
	public void testCalcularTotalCostToHomeWitch6Installments() {
		Order order = aggregatorService.registerOrder(1, client_aux.getId(), product_aux.getId(), "toHome", "installments6");
		double price = order.calculateTotalCost();
		assertEquals(price, 7200.00 );
	}
	
	

	
	
	
	
	
	
	


}