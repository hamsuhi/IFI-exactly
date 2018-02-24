/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.test.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.iservice.IVehicleServcie;
import com.example.model.Vehicle;
import com.example.service.VehicleService;
import com.example.test.AbstractTest;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
public class TestVehicleService implements AbstractTest {
	@Autowired
	private IVehicleServcie vehicleService;

	Vehicle vehicle = new Vehicle();

	@Test
	@Override
	public void findAll() {

	}

	@Test
	@Override
	public void testAdd() {

	}

	@Test
	@Override
	public void testUpdate() {

	}

	@Test
	@Override
	public void testDelete() {

	}

	@Test
	@Override
	public void findById() {

	}

}
