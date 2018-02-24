/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 21, 2018, Nguyen Thanh Huong 
 */
package com.example.test.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.iservice.IBookingService;
import com.example.test.AbstractTest;


/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestBookingService implements AbstractTest{

	@Autowired
	private IBookingService bookingService;

	@Test
	@Override
	public void findAll() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testAdd() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testDelete() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void findById() {
		
		
	}


}
