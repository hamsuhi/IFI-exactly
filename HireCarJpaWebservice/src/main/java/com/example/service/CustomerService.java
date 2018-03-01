/**
 * Copyright(C) 2018 IFI Solution
 * TenClass.java, Feb 8, 2018, Nguyen Thanh Huong 
 */
package com.example.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.iservice.ICustomerService;
import com.example.model.Custromer;
import com.example.repository.ICustomerRepository;

/**
 * @author Nguyễn Thanh Hương
 * 
 */
@Service
public class CustomerService implements ICustomerService {
	private Logger log = LogManager.getLogger(CustomerService.class);

	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public List<Custromer> findAllCustome() {
		List<Custromer> lst = customerRepository.findAll();
		for (Custromer c : lst) {
			c.toString();
		}
		return lst;
	}

	@Override
	public Custromer findCustomerById(int id) {
		Custromer c = customerRepository.findOne(id);
		if (c != null) {
			log.info("Da tim thay customer by id:" + c.toString());
		}
		log.error("Khong tim thay customer by Id");
		return c;
	}

	@Override
	public boolean addCustomer(Custromer customer) {
		Custromer c = customerRepository.save(customer);
		if (customerRepository.exists(c.getCustomerId())) {
			log.info("Don't add because Custromer is existed: " + c.getCustomerName());
			return false;
		}
		log.info("****Add Customer succedd! " + c.toString());
		return true;
	}

	@Override
	public boolean updateCustomer(int id, String addressLine1, String addressLine2, String addressLine3, String country,
			String county, String customerDetails, String customerName, String emailAddress, String gender,
			String phoneNumber, String town) {
		Custromer c = customerRepository.findOne(id);
		if (c != null) {
			c.setAddressLine1(addressLine1);
			c.setAddressLine2(addressLine2);
			c.setAddressLine3(addressLine3);
			c.setCountry(country);
			c.setCounty(county);
			c.setCustomerDetails(customerDetails);
			c.setCustomerName(customerName);
			c.setEmailAddress(emailAddress);
			c.setGender(gender);
			c.setPhoneNumber(phoneNumber);
			c.setTown(town);
			customerRepository.saveAndFlush(c);
			log.info("****Update customer scceed!!! " + c.toString());
			return true;
		}
		log.error("@.@_________Update customer failed!________@.@");
		return false;
	}

	@Override
	public boolean deleteCustomer(int id) {
		Custromer c = customerRepository.findOne(id);
		if (c != null) {
			customerRepository.delete(id);
			log.info("****Delete customer succedd!  *****");
			return true;
		}
		log.error("@.@_________Delete customer failed!_______@.@");
		return false;
	}
}
