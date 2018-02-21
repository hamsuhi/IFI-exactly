/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 13, 2018, Nguyen Thanh Huong 
 * Khong co f/th testById vi no duoc su dung trong nhung ham test o tren
 */
package com.example.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nguyễn Thanh Hương
 * 
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootConfiguration
public abstract interface AbstractTest {
	Logger logger = LoggerFactory.getLogger(AbstractTest.class);

	void findAll();
	
	void findById();

	void testAdd();

	void testUpdate();

	void testDelete();

}
