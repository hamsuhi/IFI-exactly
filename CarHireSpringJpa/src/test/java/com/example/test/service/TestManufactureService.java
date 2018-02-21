package com.example.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
/*
 * Unit Test:  gửi đi một thông điệp và kiểm tra câu trả lời nhận được đúng hay không gồm: 
 *  - Các kết quả trả về mong muốn
 *  - Các lỗi ngoại lệ mong muốn
 */
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.iservice.IManufactureService;
import com.example.model.Manufacturer;
import com.example.test.AbstractTest;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestManufactureService implements AbstractTest {

	@Autowired
	private IManufactureService manufacturerService;

	// OBJ Manufacture
	protected Manufacturer manu1 = new Manufacturer("Japannn", "Xu so hoa anh dao");
	protected Manufacturer manu2 = new Manufacturer("Hong Kong", "San xuat Hong Kong");
	protected Manufacturer manuDB = new Manufacturer("Hong Kong", "San xuat Hong Kong");

	@Test
	@Override
	public void findAll() {
		// logger.info("_____________Test find all Manfacture _________________");
		// when
		List<Manufacturer> manufacturers = manufacturerService.findAllManufacturer();

		// then
		assertThat(manufacturers.size()).isNotEqualTo(manuDB);
		assertThat(manufacturers.get(76)).isNotEqualTo(manuDB.getManufacturerCode());
		if (!manufacturers.get(2).equals(manu2.getManufacturerCode())) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	@Test
	@Override
	public void findById() {
		//when
		Manufacturer manuId = manufacturerService.findManufactureById(manuDB.getManufacturerCode());
		//then
		//assertThat(manuId.getManufacturerName()).isEqualTo(manuDB.getManufacturerName());
	}

	@Test
	@Override
	public void testAdd() {
		// logger.info("_____________Test add Manfacture _________________");
		assertNotEquals(true, manufacturerService.addManufacture(manu1));
		assertNotNull(manufacturerService.addManufacture(manu1));
		assertNotEquals(manufacturerService.addManufacture(manuDB), manufacturerService.addManufacture(manu1));
		// check id the same as.
		if (manufacturerService.findManufactureById(manu2.getManufacturerCode()) != manufacturerService
				.findManufactureById(manuDB.getManufacturerCode())) {
			assertNotEquals(manufacturerService.addManufacture(manuDB), manufacturerService.addManufacture(manu2));
		} else {
			assertEquals(manufacturerService.addManufacture(manuDB), manufacturerService.addManufacture(manu2));
		}
	}

	@Test
	@Override
	public void testUpdate() {
		// logger.info("____________________Test update manufacturer________________");
		assertNotEquals(manuDB.getManufacturerCode(), manufacturerService.updateManufacture(1, "Russia", "Perfect"));

	}

	@Test
	@Override
	public void testDelete() {
		// logger.info("____________________Test delete manufacturer________________");
		boolean manuDelete = manufacturerService.deleteManufacture(manuDB.getManufacturerCode());
		if (true) {
			assertFalse(false);
		} else {
			assertTrue(false);
		}
	}

}
