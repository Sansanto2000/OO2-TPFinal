package com.demo.finaloo2;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.demo.service.AggregatorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = {
		//"jdbc:mysql://localhost:3308/dboo2", 
		//"spring.datasource.username=root",
		//"spring.datasource.password=root",
		//"spring.jpa.hibernate.ddl-auto=create-drop"
	})
class Finaloo2ApplicationTests {
	
	@Test
	void contextLoads() {
		
	}
	
	@Autowired
	AggregatorService proxyService;


}
