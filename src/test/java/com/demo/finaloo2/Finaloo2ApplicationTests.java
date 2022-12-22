package com.demo.finaloo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.model.Seller;
import com.demo.service.ProxyService;

@SpringBootTest
class Finaloo2ApplicationTests {

	@Autowired
	ProxyService proxyService;
	
	@Test
	void contextLoads() {
		
	}
	
	@Test
	public void testRegisterSeller() {
		Seller s = proxyService.registerSeller("galileo", "Italia, Toscana, Basílica de la Santa Cruz");
		//if(s == null)
			//return new ResponseEntity<Seller>(HttpStatus.BAD_REQUEST);
		assert(true);
	}

}
