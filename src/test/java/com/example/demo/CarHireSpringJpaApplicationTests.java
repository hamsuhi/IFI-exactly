package com.example.demo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.service.ManufacturerService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class CarHireSpringJpaApplicationTests {
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Test
	public void testprintms() {
//		ManufacturerService a = new ManufacturerService();
		assertTrue(manufacturerService.updateManufacture(2, "japan", "san xuat tai japan"));
	}

}
